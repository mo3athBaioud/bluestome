var ${field}Store = new Ext.data.SimpleStore({
  fields : ['value', 'text'],
  data : [
  #foreach($code in $codeList)
    ['${code.code}', '#if(${showCode}=="true")${code.code} #end${code.codedesc}'],
  #end
  ]
});