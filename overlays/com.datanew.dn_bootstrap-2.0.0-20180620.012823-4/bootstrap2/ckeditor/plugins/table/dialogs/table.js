(function(){function b(r){for(var j=0,h=0,i=0,g,k=r.$.rows.length;i<k;i++){g=r.$.rows[i];for(var m=j=0,q,o=g.cells.length;m<o;m++){q=g.cells[m],j+=q.colSpan}j>h&&(h=j)}return h}function d(f){return function(){var g=this.getValue(),g=!!(CKEDITOR.dialog.validate.integer()(g)&&0<g);g||(alert(f),this.select());return g}}function e(g,i){var k=function(f){return new CKEDITOR.dom.element(f,g.document)},h=g.editable(),j=g.plugins.dialogadvtab;return{title:g.lang.table.title,minWidth:310,minHeight:CKEDITOR.env.ie?310:280,onLoad:function(){var l=this,f=l.getContentElement("advanced","advStyles");if(f){f.on("change",function(){var m=this.getStyle("width",""),n=l.getContentElement("info","txtWidth");n&&n.setValue(m,!0);m=this.getStyle("height","");(n=l.getContentElement("info","txtHeight"))&&n.setValue(m,!0)})}},onShow:function(){var p=g.getSelection(),q=p.getRanges(),l,r=this.getContentElement("info","txtRows"),o=this.getContentElement("info","txtCols"),n=this.getContentElement("info","txtWidth"),f=this.getContentElement("info","txtHeight");"tableProperties"==i&&((p=p.getSelectedElement())&&p.is("table")?l=p:0<q.length&&(CKEDITOR.env.webkit&&q[0].shrink(CKEDITOR.NODE_ELEMENT),l=g.elementPath(q[0].getCommonAncestor(!0)).contains("table",1)),this._.selectedElement=l);l?(this.setupContent(l),r&&r.disable(),o&&o.disable()):(r&&r.enable(),o&&o.enable());n&&n.onChange();f&&f.onChange()},onOk:function(){var v=g.getSelection(),w=this._.selectedElement&&v.createBookmarks(),y=this._.selectedElement||k("table"),x={};this.commitContent(x,y);if(x.info){x=x.info;if(!this._.selectedElement){for(var t=y.append(k("tbody")),u=parseInt(x.txtRows,10)||0,o=parseInt(x.txtCols,10)||0,r=0;r<u;r++){for(var s=t.append(k("tr")),q=0;q<o;q++){s.append(k("td")).appendBogus()}}}u=x.selHeaders;if(!y.$.tHead&&("row"==u||"both"==u)){s=y.getElementsByTag("thead").getItem(0);t=y.getElementsByTag("tbody").getItem(0);o=t.getElementsByTag("tr").getItem(0);s||(s=new CKEDITOR.dom.element("thead"),s.insertBefore(t));for(r=0;r<o.getChildCount();r++){t=o.getChild(r),t.type!=CKEDITOR.NODE_ELEMENT||t.data("cke-bookmark")||(t.renameNode("th"),t.setAttribute("scope","col"))}s.append(o.remove())}if(null!==y.$.tHead&&"row"!=u&&"both"!=u){s=new CKEDITOR.dom.element(y.$.tHead);t=y.getElementsByTag("tbody").getItem(0);for(q=t.getFirst();0<s.getChildCount();){o=s.getFirst();for(r=0;r<o.getChildCount();r++){t=o.getChild(r),t.type==CKEDITOR.NODE_ELEMENT&&(t.renameNode("td"),t.removeAttribute("scope"))}o.insertBefore(q)}s.remove()}if(!this.hasColumnHeaders&&("col"==u||"both"==u)){for(s=0;s<y.$.rows.length;s++){t=new CKEDITOR.dom.element(y.$.rows[s].cells[0]),t.renameNode("th"),t.setAttribute("scope","row")}}if(this.hasColumnHeaders&&"col"!=u&&"both"!=u){for(r=0;r<y.$.rows.length;r++){s=new CKEDITOR.dom.element(y.$.rows[r]),"tbody"==s.getParent().getName()&&(t=new CKEDITOR.dom.element(s.$.cells[0]),t.renameNode("td"),t.removeAttribute("scope"))}}x.txtHeight?y.setStyle("height",x.txtHeight):y.removeStyle("height");x.txtWidth?y.setStyle("width",x.txtWidth):y.removeStyle("width");y.getAttribute("style")||y.removeAttribute("style")}if(this._.selectedElement){try{v.selectBookmarks(w)}catch(n){}}else{g.insertElement(y),setTimeout(function(){var f=new CKEDITOR.dom.element(y.$.rows[0].cells[0]),l=g.createRange();l.moveToPosition(f,CKEDITOR.POSITION_AFTER_START);l.select()},0)}},contents:[{id:"info",label:g.lang.table.title,elements:[{type:"hbox",widths:[null,null],styles:["vertical-align:top"],children:[{type:"vbox",padding:0,children:[{type:"text",id:"txtRows","default":3,label:g.lang.table.rows,required:!0,controlStyle:"width:5em",validate:d(g.lang.table.invalidRows),setup:function(f){this.setValue(f.$.rows.length)},commit:a},{type:"text",id:"txtCols","default":2,label:g.lang.table.columns,required:!0,controlStyle:"width:5em",validate:d(g.lang.table.invalidCols),setup:function(f){this.setValue(b(f))},commit:a},{type:"html",html:"\x26nbsp;"},{type:"select",id:"selHeaders",requiredContent:"th","default":"",label:g.lang.table.headers,items:[[g.lang.table.headersNone,""],[g.lang.table.headersRow,"row"],[g.lang.table.headersColumn,"col"],[g.lang.table.headersBoth,"both"]],setup:function(m){var l=this.getDialog();l.hasColumnHeaders=!0;for(var f=0;f<m.$.rows.length;f++){var n=m.$.rows[f].cells[0];if(n&&"th"!=n.nodeName.toLowerCase()){l.hasColumnHeaders=!1;break}}null!==m.$.tHead?this.setValue(l.hasColumnHeaders?"both":"row"):this.setValue(l.hasColumnHeaders?"col":"")},commit:a},{type:"text",id:"txtBorder",requiredContent:"table[border]","default":g.filter.check("table[border]")?1:0,label:g.lang.table.border,controlStyle:"width:3em",validate:CKEDITOR.dialog.validate.number(g.lang.table.invalidBorder),setup:function(f){this.setValue(f.getAttribute("border")||"")},commit:function(f,l){this.getValue()?l.setAttribute("border",this.getValue()):l.removeAttribute("border")}},{id:"cmbAlign",type:"select",requiredContent:"table[align]","default":"",label:g.lang.common.align,items:[[g.lang.common.notSet,""],[g.lang.common.alignLeft,"left"],[g.lang.common.alignCenter,"center"],[g.lang.common.alignRight,"right"]],setup:function(f){this.setValue(f.getAttribute("align")||"")},commit:function(f,l){this.getValue()?l.setAttribute("align",this.getValue()):l.removeAttribute("align")}}]},{type:"vbox",padding:0,children:[{type:"hbox",widths:["5em"],children:[{type:"text",id:"txtWidth",requiredContent:"table{width}",controlStyle:"width:5em",label:g.lang.common.width,title:g.lang.common.cssLengthTooltip,"default":g.filter.check("table{width}")?500>h.getSize("width")?"100%":500:0,getValue:c,validate:CKEDITOR.dialog.validate.cssLength(g.lang.common.invalidCssLength.replace("%1",g.lang.common.width)),onChange:function(){var f=this.getDialog().getContentElement("advanced","advStyles");f&&f.updateStyle("width",this.getValue())},setup:function(f){f=f.getStyle("width");this.setValue(f)},commit:a}]},{type:"hbox",widths:["5em"],children:[{type:"text",id:"txtHeight",requiredContent:"table{height}",controlStyle:"width:5em",label:g.lang.common.height,title:g.lang.common.cssLengthTooltip,"default":"",getValue:c,validate:CKEDITOR.dialog.validate.cssLength(g.lang.common.invalidCssLength.replace("%1",g.lang.common.height)),onChange:function(){var f=this.getDialog().getContentElement("advanced","advStyles");f&&f.updateStyle("height",this.getValue())},setup:function(f){(f=f.getStyle("height"))&&this.setValue(f)},commit:a}]},{type:"html",html:"\x26nbsp;"},{type:"text",id:"txtCellSpace",requiredContent:"table[cellspacing]",controlStyle:"width:3em",label:g.lang.table.cellSpace,"default":g.filter.check("table[cellspacing]")?1:0,validate:CKEDITOR.dialog.validate.number(g.lang.table.invalidCellSpacing),setup:function(f){this.setValue(f.getAttribute("cellSpacing")||"")},commit:function(f,l){this.getValue()?l.setAttribute("cellSpacing",this.getValue()):l.removeAttribute("cellSpacing")}},{type:"text",id:"txtCellPad",requiredContent:"table[cellpadding]",controlStyle:"width:3em",label:g.lang.table.cellPad,"default":g.filter.check("table[cellpadding]")?1:0,validate:CKEDITOR.dialog.validate.number(g.lang.table.invalidCellPadding),setup:function(f){this.setValue(f.getAttribute("cellPadding")||"")},commit:function(f,l){this.getValue()?l.setAttribute("cellPadding",this.getValue()):l.removeAttribute("cellPadding")}}]}]},{type:"html",align:"right",html:""},{type:"vbox",padding:0,children:[{type:"text",id:"txtCaption",requiredContent:"caption",label:g.lang.table.caption,setup:function(f){this.enable();f=f.getElementsByTag("caption");if(0<f.count()){f=f.getItem(0);var l=f.getFirst(CKEDITOR.dom.walker.nodeType(CKEDITOR.NODE_ELEMENT));l&&!l.equals(f.getBogus())?(this.disable(),this.setValue(f.getText())):(f=CKEDITOR.tools.trim(f.getText()),this.setValue(f))}},commit:function(l,m){if(this.isEnabled()){var f=this.getValue(),n=m.getElementsByTag("caption");if(f){0<n.count()?(n=n.getItem(0),n.setHtml("")):(n=new CKEDITOR.dom.element("caption",g.document),m.append(n,!0)),n.append(new CKEDITOR.dom.text(f,g.document))}else{if(0<n.count()){for(f=n.count()-1;0<=f;f--){n.getItem(f).remove()}}}}}},{type:"text",id:"txtSummary",bidi:!0,requiredContent:"table[summary]",label:g.lang.table.summary,setup:function(f){this.setValue(f.getAttribute("summary")||"")},commit:function(f,l){this.getValue()?l.setAttribute("summary",this.getValue()):l.removeAttribute("summary")}}]}]},j&&j.createAdvancedTab(g,null,"table")]}}var c=CKEDITOR.tools.cssLength,a=function(g){var h=this.id;g.info||(g.info={});g.info[h]=this.getValue()};CKEDITOR.dialog.add("table",function(f){return e(f,"table")});CKEDITOR.dialog.add("tableProperties",function(f){return e(f,"tableProperties")})})();