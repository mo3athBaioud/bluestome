/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.apache.mina.core.filterchain;

import com.skymobi.android.commons.logger.Logger;
import com.skymobi.android.commons.logger.LoggerFactory;

import org.apache.mina.core.filterchain.IoFilter.NextFilter;
import org.apache.mina.core.filterchain.IoFilterChain.Entry;
import org.apache.mina.core.session.IoSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The default implementation of {@link IoFilterChainBuilder} which is useful
 * in most cases.  {@link DefaultIoFilterChainBuilder} has an identical interface
 * with {@link IoFilter}; it contains a list of {@link IoFilter}s that you can
 * modify. The {@link IoFilter}s which are added to this builder will be appended
 * to the {@link IoFilterChain} when {@link #buildFilterChain(IoFilterChain)} is
 * invoked.
 * <p>
 * However, the identical interface doesn't mean that it behaves in an exactly
 * same way with {@link IoFilterChain}.  {@link DefaultIoFilterChainBuilder}
 * doesn't manage the life cycle of the {@link IoFilter}s at all, and the
 * existing {@link IoSession}s won't get affected by the changes in this builder.
 * {@link IoFilterChainBuilder}s affect only newly created {@link IoSession}s.
 *
 * <pre>
 * IoAcceptor acceptor = ...;
 * DefaultIoFilterChainBuilder builder = acceptor.getFilterChain();
 * builder.addLast( "myFilter", new MyFilter() );
 * ...
 * </pre>
 *
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 * @org.apache.xbean.XBean
 */
public class DefaultIoFilterChainBuilder implements IoFilterChainBuilder {
    
    private final static Logger LOGGER = 
        LoggerFactory.getLogger(DefaultIoFilterChainBuilder.class);
    private final List<Entry> entries;

    /**
     * Creates a new instance with an empty filter list.
     */
    public DefaultIoFilterChainBuilder() {
        entries = new CopyOnWriteArrayList<Entry>();
    }

    /**
     * Creates a new copy of the specified {@link DefaultIoFilterChainBuilder}.
     */
    public DefaultIoFilterChainBuilder(DefaultIoFilterChainBuilder filterChain) {
        if (filterChain == null) {
            throw new IllegalArgumentException("filterChain");
        }
        entries = new CopyOnWriteArrayList<Entry>(filterChain.entries);
    }

    /**
     * @see IoFilterChain#getEntry(String)
     */
    public Entry getEntry(String name) {
        for (Entry e: entries) {
            if (e.getName().equals(name)) {
                return e;
            }
        }

        return null;
    }

    /**
     * @see IoFilterChain#getEntry(IoFilter)
     */
    public Entry getEntry(IoFilter filter) {
        for (Entry e: entries) {
            if (e.getFilter() == filter) {
                return e;
            }
        }

        return null;
    }

    /**
     * @see IoFilterChain#getEntry(Class)
     */
    public Entry getEntry(Class<? extends IoFilter> filterType) {
        for (Entry e: entries) {
            if (filterType.isAssignableFrom(e.getFilter().getClass())) {
                return e;
            }
        }

        return null;
    }

    /**
     * @see IoFilterChain#get(String)
     */
    public IoFilter get(String name) {
        Entry e = getEntry(name);
        if (e == null) {
            return null;
        }

        return e.getFilter();
    }

    /**
     * @see IoFilterChain#get(Class)
     */
    public IoFilter get(Class<? extends IoFilter> filterType) {
        Entry e = getEntry(filterType);
        if (e == null) {
            return null;
        }

        return e.getFilter();
    }

    /**
     * @see IoFilterChain#getAll()
     */
    public List<Entry> getAll() {
        return new ArrayList<Entry>(entries);
    }

    /**
     * @see IoFilterChain#getAllReversed()
     */
    public List<Entry> getAllReversed() {
        List<Entry> result = getAll();
        Collections.reverse(result);
        return result;
    }

    /**
     * @see IoFilterChain#contains(String)
     */
    public boolean contains(String name) {
        return getEntry(name) != null;
    }

    /**
     * @see IoFilterChain#contains(IoFilter)
     */
    public boolean contains(IoFilter filter) {
        return getEntry(filter) != null;
    }

    /**
     * @see IoFilterChain#contains(Class)
     */
    public boolean contains(Class<? extends IoFilter> filterType) {
        return getEntry(filterType) != null;
    }

    /**
     * @see IoFilterChain#addFirst(String, IoFilter)
     */
    public synchronized void addFirst(String name, IoFilter filter) {
        register(0, new EntryImpl(name, filter));
    }

    /**
     * @see IoFilterChain#addLast(String, IoFilter)
     */
    public synchronized void addLast(String name, IoFilter filter) {
        register(entries.size(), new EntryImpl(name, filter));
    }

    /**
     * @see IoFilterChain#addBefore(String, String, IoFilter)
     */
    public synchronized void addBefore(String baseName, String name,
            IoFilter filter) {
        checkBaseName(baseName);

        for (ListIterator<Entry> i = entries.listIterator(); i.hasNext();) {
            Entry base = i.next();
            if (base.getName().equals(baseName)) {
                register(i.previousIndex(), new EntryImpl(name, filter));
                break;
            }
        }
    }

    /**
     * @see IoFilterChain#addAfter(String, String, IoFilter)
     */
    public synchronized void addAfter(String baseName, String name,
            IoFilter filter) {
        checkBaseName(baseName);

        for (ListIterator<Entry> i = entries.listIterator(); i.hasNext();) {
            Entry base = i.next();
            if (base.getName().equals(baseName)) {
                register(i.nextIndex(), new EntryImpl(name, filter));
                break;
            }
        }
    }

    /**
     * @see IoFilterChain#remove(String)
     */
    public synchronized IoFilter remove(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name");
        }

        for (ListIterator<Entry> i = entries.listIterator(); i.hasNext();) {
            Entry e = i.next();
            if (e.getName().equals(name)) {
                entries.remove(i.previousIndex());
                return e.getFilter();
            }
        }

        throw new IllegalArgumentException("Unknown filter name: " + name);
    }

    /**
     * @see IoFilterChain#remove(IoFilter)
     */
    public synchronized IoFilter remove(IoFilter filter) {
        if (filter == null) {
            throw new IllegalArgumentException("filter");
        }

        for (ListIterator<Entry> i = entries.listIterator(); i.hasNext();) {
            Entry e = i.next();
            if (e.getFilter() == filter) {
                entries.remove(i.previousIndex());
                return e.getFilter();
            }
        }

        throw new IllegalArgumentException("Filter not found: " + filter.getClass().getName());
    }

    /**
     * @see IoFilterChain#remove(Class)
     */
    public synchronized IoFilter remove(Class<? extends IoFilter> filterType) {
        if (filterType == null) {
            throw new IllegalArgumentException("filterType");
        }

        for (ListIterator<Entry> i = entries.listIterator(); i.hasNext();) {
            Entry e = i.next();
            if (filterType.isAssignableFrom(e.getFilter().getClass())) {
                entries.remove(i.previousIndex());
                return e.getFilter();
            }
        }

        throw new IllegalArgumentException("Filter not found: " + filterType.getName());
    }

    public synchronized IoFilter replace(String name, IoFilter newFilter) {
        checkBaseName(name);
        EntryImpl e = (EntryImpl)getEntry(name);
        IoFilter oldFilter = e.getFilter();
        e.setFilter(newFilter);
        return oldFilter;
    }

    public synchronized void replace(IoFilter oldFilter, IoFilter newFilter) {
        for (Entry e : entries) {
            if (e.getFilter() == oldFilter) {
                ((EntryImpl) e).setFilter(newFilter);
                return;
            }
        }
        throw new IllegalArgumentException("Filter not found: "
                + oldFilter.getClass().getName());
    }

    public synchronized void replace(Class<? extends IoFilter> oldFilterType,
            IoFilter newFilter) {
        for (Entry e : entries) {
            if (oldFilterType.isAssignableFrom(e.getFilter().getClass())) {
                ((EntryImpl) e).setFilter(newFilter);
                return;
            }
        }
        throw new IllegalArgumentException("Filter not found: "
                + oldFilterType.getName());
    }

    /**
     * @see IoFilterChain#clear()
     */
    public synchronized void clear() {
        entries.clear();
    }
    
    /**
     * Clears the current list of filters and adds the specified
     * filter mapping to this builder.  Please note that you must specify
     * a {@link Map} implementation that iterates the filter mapping in the
     * order of insertion such as {@link LinkedHashMap}.  Otherwise, it will
     * throw an {@link IllegalArgumentException}.
     */
    public void setFilters(Map<String, ? extends IoFilter> filters) {
        if (filters == null) {
            throw new IllegalArgumentException("filters");
        }
        
        if (!isOrderedMap(filters)) {
            throw new IllegalArgumentException(
                    "filters is not an ordered map. Please try " + 
                    LinkedHashMap.class.getName() + ".");
        }

        filters = new LinkedHashMap<String, IoFilter>(filters);
        for (Map.Entry<String, ? extends IoFilter> e: filters.entrySet()) {
            if (e.getKey() == null) {
                throw new IllegalArgumentException("filters contains a null key.");
            }
            if (e.getValue() == null) {
                throw new IllegalArgumentException("filters contains a null value.");
            }
        }
        
        synchronized (this) {
            clear();
            for (Map.Entry<String, ? extends IoFilter> e: filters.entrySet()) {
                addLast(e.getKey(), e.getValue());
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    private boolean isOrderedMap(Map map) {
        Class<?> mapType = map.getClass();
        if (LinkedHashMap.class.isAssignableFrom(mapType)) {
            LOGGER.debug(mapType.getSimpleName() + " is an ordered map.");
            return true;
        }
        
        LOGGER.debug(mapType.getName() + " is not a " + LinkedHashMap.class.getSimpleName());

        // Detect Jakarta Commons Collections OrderedMap implementations.
        Class<?> type = mapType;
        while (type != null) {
            for (Class<?> i: type.getInterfaces()) {
                if (i.getName().endsWith("OrderedMap")) {
                    LOGGER.debug(
                            mapType.getSimpleName() +
                            " is an ordered map (guessed from that it " +
                            " implements OrderedMap interface.)");
                    return true;
                }
            }
            type = type.getSuperclass();
        }
        
        LOGGER.debug(
                mapType.getName() +
                " doesn't implement OrderedMap interface.");
        
        // Last resort: try to create a new instance and test if it maintains
        // the insertion order.
        LOGGER.debug(
                "Last resort; trying to create a new map instance with a " +
                "default constructor and test if insertion order is " +
                "maintained.");
        
        Map newMap;
        try {
            newMap = (Map) mapType.newInstance();
        } catch (Exception e) {
            LOGGER.debug(
                    "Failed to create a new map instance of '" + 
                    mapType.getName() +"'.", e);
            return false;
        }
        
        Random rand = new Random();
        List<String> expectedNames = new ArrayList<String>();
        IoFilter dummyFilter = new IoFilterAdapter();
        for (int i = 0; i < 65536; i ++) {
            String filterName;
            do {
                filterName = String.valueOf(rand.nextInt());
            } while (newMap.containsKey(filterName));
            
            newMap.put(filterName, dummyFilter);
            expectedNames.add(filterName);

            Iterator<String> it = expectedNames.iterator();
            for (Object key: newMap.keySet()) {
                if (!it.next().equals(key)) {
                    LOGGER.debug(
                            "The specified map didn't pass the insertion " +
                            "order test after " + (i + 1) + " tries.");
                    return false;
                }
            }
        }
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(
                    "The specified map passed the insertion order test.");
        }
        return true;
    }

    public void buildFilterChain(IoFilterChain chain) throws Exception {
        for (Entry e : entries) {
            chain.addLast(e.getName(), e.getFilter());
        }
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("{ ");

        boolean empty = true;

        for (Entry e : entries) {
            if (!empty) {
                buf.append(", ");
            } else {
                empty = false;
            }

            buf.append('(');
            buf.append(e.getName());
            buf.append(':');
            buf.append(e.getFilter());
            buf.append(')');
        }

        if (empty) {
            buf.append("empty");
        }

        buf.append(" }");

        return buf.toString();
    }

    private void checkBaseName(String baseName) {
        if (baseName == null) {
            throw new IllegalArgumentException("baseName");
        }

        if (!contains(baseName)) {
            throw new IllegalArgumentException("Unknown filter name: "
                    + baseName);
        }
    }

    private void register(int index, Entry e) {
        if (contains(e.getName())) {
            throw new IllegalArgumentException(
                    "Other filter is using the same name: " + e.getName());
        }

        entries.add(index, e);
    }

    private class EntryImpl implements Entry {
        private final String name;
        private volatile IoFilter filter;

        private EntryImpl(String name, IoFilter filter) {
            if (name == null) {
                throw new IllegalArgumentException("name");
            }
            if (filter == null) {
                throw new IllegalArgumentException("filter");
            }

            this.name = name;
            this.filter = filter;
        }

        public String getName() {
            return name;
        }

        public IoFilter getFilter() {
            return filter;
        }

        private void setFilter(IoFilter filter) {
            this.filter = filter;
        }

        public NextFilter getNextFilter() {
            throw new IllegalStateException();
        }

        @Override
        public String toString() {
            return "(" + getName() + ':' + filter + ')';
        }

        public void addAfter(String name, IoFilter filter) {
            DefaultIoFilterChainBuilder.this.addAfter(getName(), name, filter);
        }

        public void addBefore(String name, IoFilter filter) {
            DefaultIoFilterChainBuilder.this.addBefore(getName(), name, filter);
        }

        public void remove() {
            DefaultIoFilterChainBuilder.this.remove(getName());
        }

        public void replace(IoFilter newFilter) {
            DefaultIoFilterChainBuilder.this.replace(getName(), newFilter);
        }
    }
}
