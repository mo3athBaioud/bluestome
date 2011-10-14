Ext.ns('EasyExt.widgets');

EasyExt.widgets.TitleBar = Ext.extend(Ext.BoxComponent, {
	initComponent: function() {
		Ext.apply(this, {
			height: 28,
			cls: 'easyext-title-bar',
			autoEl: {
				tag: 'table',
				cellSpacing: 0,
				cellPadding: 0,
				border: 0,
				children: [{
					tag: 'tr',
					children: [{
						tag: 'td',
						cls: 'left'
					}, {
						tag: 'td',
						html: this.title
					}, {
						tag: 'td',
						cls: 'right'
					}]
				}]
			}
		});
		EasyExt.widgets.TitleBar.superclass.initComponent.apply(this, arguments);
	}
});

Ext.reg('easyext-title-bar', EasyExt.widgets.TitleBar);