(function(f){var C={event:{DRAG:"ztree_drag",DROP:"ztree_drop",RENAME:"ztree_rename",DRAGMOVE:"ztree_dragmove"},id:{EDIT:"_edit",INPUT:"_input",REMOVE:"_remove"},move:{TYPE_INNER:"inner",TYPE_PREV:"prev",TYPE_NEXT:"next"},node:{CURSELECTED_EDIT:"curSelectedNode_Edit",TMPTARGET_TREE:"tmpTargetzTree",TMPTARGET_NODE:"tmpTargetNode"}},m={edit:{enable:false,editNameSelectAll:false,showRemoveBtn:true,showRenameBtn:true,removeTitle:"remove",renameTitle:"rename",drag:{autoExpandTrigger:false,isCopy:true,isMove:true,prev:true,next:true,inner:true,minMoveSize:5,borderMax:10,borderMin:-5,maxShowNodeNum:5,autoOpenTime:500}},view:{addHoverDom:null,removeHoverDom:null},callback:{beforeDrag:null,beforeDragOpen:null,beforeDrop:null,beforeEditName:null,beforeRename:null,onDrag:null,onDragMove:null,onDrop:null,onRename:null}},x=function(E){var F=A.getRoot(E),D=A.getRoots();F.curEditNode=null;F.curEditInput=null;F.curHoverNode=null;F.dragFlag=0;F.dragNodeShowBefore=[];F.dragMaskList=new Array();D.showHoverDom=true},c=function(D){},n=function(D){var E=D.treeObj;var F=q.event;E.bind(F.RENAME,function(H,J,I,G){v.apply(D.callback.onRename,[H,J,I,G])});E.bind(F.DRAG,function(H,G,J,I){v.apply(D.callback.onDrag,[G,J,I])});E.bind(F.DRAGMOVE,function(H,G,J,I){v.apply(D.callback.onDragMove,[G,J,I])});E.bind(F.DROP,function(J,I,L,K,M,H,G){v.apply(D.callback.onDrop,[I,L,K,M,H,G])})},z=function(D){var E=D.treeObj;var F=q.event;E.unbind(F.RENAME);E.unbind(F.DRAG);E.unbind(F.DRAGMOVE);E.unbind(F.DROP)},o=function(K){var L=K.target,O=A.getSetting(K.data.treeId),M=K.relatedTarget,I="",E=null,F="",J="",D=null,H=null,G=null;if(v.eqs(K.type,"mouseover")){G=v.getMDom(O,L,[{tagName:"a",attrName:"treeNode"+q.id.A}]);if(G){I=v.getNodeMainDom(G).id;F="hoverOverNode"}}else{if(v.eqs(K.type,"mouseout")){G=v.getMDom(O,M,[{tagName:"a",attrName:"treeNode"+q.id.A}]);if(!G){I="remove";F="hoverOutNode"}}else{if(v.eqs(K.type,"mousedown")){G=v.getMDom(O,L,[{tagName:"a",attrName:"treeNode"+q.id.A}]);if(G){I=v.getNodeMainDom(G).id;F="mousedownNode"}}}}if(I.length>0){E=A.getNodeCache(O,I);switch(F){case"mousedownNode":D=l.onMousedownNode;break;case"hoverOverNode":D=l.onHoverOverNode;break;case"hoverOutNode":D=l.onHoverOutNode;break}}var N={stop:false,node:E,nodeEventType:F,nodeEventCallback:D,treeEventType:J,treeEventCallback:H};return N},w=function(F,J,I,D,H,E,G){if(!I){return}I.isHover=false;I.editNameFlag=false},k=function(E,D){D.cancelEditName=function(G){var F=A.getRoot(this.setting);if(!F.curEditNode){return}j.cancelCurEditNode(this.setting,G?G:null,true)};D.copyNode=function(J,I,H,L){if(!I){return null}if(J&&!J.isParent&&this.setting.data.keep.leaf&&H===q.move.TYPE_INNER){return null}var K=this,F=v.clone(I);if(!J){J=null;H=q.move.TYPE_INNER}if(H==q.move.TYPE_INNER){function G(){j.addNodes(K.setting,J,-1,[F],L)}if(v.canAsync(this.setting,J)){j.asyncNode(this.setting,J,L,G)}else{G()}}else{j.addNodes(this.setting,J.parentNode,-1,[F],L);j.moveNode(this.setting,J,F,H,false,L)}return F};D.editName=function(F){if(!F||!F.tId||F!==A.getNodeCache(this.setting,F.tId)){return}if(F.parentTId){j.expandCollapseParentNode(this.setting,F.getParentNode(),true)}j.editNode(this.setting,F)};D.moveNode=function(H,G,F,K){if(!G){return G}if(H&&!H.isParent&&this.setting.data.keep.leaf&&F===q.move.TYPE_INNER){return null}else{if(H&&((G.parentTId==H.tId&&F==q.move.TYPE_INNER)||i(G,this.setting).find("#"+H.tId).length>0)){return null}else{if(!H){H=null}}}var J=this;function I(){j.moveNode(J.setting,H,G,F,false,K)}if(v.canAsync(this.setting,H)&&F===q.move.TYPE_INNER){j.asyncNode(this.setting,H,K,I)}else{I()}return G};D.setEditable=function(F){this.setting.edit.enable=F;return this.refresh()}},p={setSonNodeLevel:function(G,D,I){if(!I){return}var H=G.data.key.children;I.level=(D)?D.level+1:0;if(!I[H]){return}for(var F=0,E=I[H].length;F<E;F++){if(I[H][F]){A.setSonNodeLevel(G,I,I[H][F])}}}},g={},l={onHoverOverNode:function(G,F){var E=A.getSetting(G.data.treeId),D=A.getRoot(E);if(D.curHoverNode!=F){l.onHoverOutNode(G)}D.curHoverNode=F;j.addHoverDom(E,F)},onHoverOutNode:function(G,F){var E=A.getSetting(G.data.treeId),D=A.getRoot(E);if(D.curHoverNode&&!A.isSelectedNode(E,D.curHoverNode)){j.removeTreeDom(E,D.curHoverNode);D.curHoverNode=null}},onMousedownNode:function(S,K){var aa,X,R=A.getSetting(S.data.treeId),W=A.getRoot(R),L=A.getRoots();if(S.button==2||!R.edit.enable||(!R.edit.drag.isCopy&&!R.edit.drag.isMove)){return true}var ad=S.target,J=A.getRoot(R).curSelectedList,T=[];if(!A.isSelectedNode(R,K)){T=[K]}else{for(aa=0,X=J.length;aa<X;aa++){if(J[aa].editNameFlag&&v.eqs(ad.tagName,"input")&&ad.getAttribute("treeNode"+q.id.INPUT)!==null){return true}T.push(J[aa]);if(T[0].parentTId!==J[aa].parentTId){T=[K];break}}}j.editNodeBlur=true;j.cancelCurEditNode(R);var ag=f(R.treeObj.get(0).ownerDocument),M=f(R.treeObj.get(0).ownerDocument.body),Z,N,ab,ac=false,ae=R,I=R,D,H,U=null,G=null,Q=null,E=q.move.TYPE_INNER,Y=S.clientX,V=S.clientY,O=(new Date()).getTime();if(v.uCanDo(R)){ag.bind("mousemove",P)}function P(a6){if(W.dragFlag==0&&Math.abs(Y-a6.clientX)<R.edit.drag.minMoveSize&&Math.abs(V-a6.clientY)<R.edit.drag.minMoveSize){return true}var a0,aW,ay,aR,aJ,aQ=R.data.key.children;M.css("cursor","pointer");if(W.dragFlag==0){if(v.apply(R.callback.beforeDrag,[R.treeId,T],true)==false){af(a6);return true}for(a0=0,aW=T.length;a0<aW;a0++){if(a0==0){W.dragNodeShowBefore=[]}ay=T[a0];if(ay.isParent&&ay.open){j.expandCollapseNode(R,ay,!ay.open);W.dragNodeShowBefore[ay.tId]=true}else{W.dragNodeShowBefore[ay.tId]=false}}W.dragFlag=1;L.showHoverDom=false;v.showIfameMask(R,true);var ai=true,al=-1;if(T.length>1){var aw=T[0].parentTId?T[0].getParentNode()[aQ]:A.getNodes(R);aJ=[];for(a0=0,aW=aw.length;a0<aW;a0++){if(W.dragNodeShowBefore[aw[a0].tId]!==undefined){if(ai&&al>-1&&(al+1)!==a0){ai=false}aJ.push(aw[a0]);al=a0}if(T.length===aJ.length){T=aJ;break}}}if(ai){D=T[0].getPreNode();H=T[T.length-1].getNextNode()}Z=i("<ul class='zTreeDragUL'></ul>",R);for(a0=0,aW=T.length;a0<aW;a0++){ay=T[a0];ay.editNameFlag=false;j.selectNode(R,ay,a0>0);j.removeTreeDom(R,ay);if(a0>R.edit.drag.maxShowNodeNum-1){continue}aR=i("<li id='"+ay.tId+"_tmp'></li>",R);aR.append(i(ay,q.id.A,R).clone());aR.css("padding","0");aR.children("#"+ay.tId+q.id.A).removeClass(q.node.CURSELECTED);Z.append(aR);if(a0==R.edit.drag.maxShowNodeNum-1){aR=i("<li id='"+ay.tId+"_moretmp'><a>  ...  </a></li>",R);Z.append(aR)}}Z.attr("id",T[0].tId+q.id.UL+"_tmp");Z.addClass(R.treeObj.attr("class"));Z.appendTo(M);N=i("<span class='tmpzTreeMove_arrow'></span>",R);N.attr("id","zTreeMove_arrow_tmp");N.appendTo(M);R.treeObj.trigger(q.event.DRAG,[a6,R.treeId,T])}if(W.dragFlag==1){if(ab&&N.attr("id")==a6.target.id&&Q&&(a6.clientX+ag.scrollLeft()+2)>(f("#"+Q+q.id.A,ab).offset().left)){var a5=f("#"+Q+q.id.A,ab);a6.target=(a5.length>0)?a5.get(0):a6.target}else{if(ab){ab.removeClass(q.node.TMPTARGET_TREE);if(Q){f("#"+Q+q.id.A,ab).removeClass(q.node.TMPTARGET_NODE+"_"+q.move.TYPE_PREV).removeClass(q.node.TMPTARGET_NODE+"_"+C.move.TYPE_NEXT).removeClass(q.node.TMPTARGET_NODE+"_"+C.move.TYPE_INNER)}}}ab=null;Q=null;ac=false;ae=R;var a1=A.getSettings();for(var aS in a1){if(a1[aS].treeId&&a1[aS].edit.enable&&a1[aS].treeId!=R.treeId&&(a6.target.id==a1[aS].treeId||f(a6.target).parents("#"+a1[aS].treeId).length>0)){ac=true;ae=a1[aS]}}var av=ag.scrollTop(),a4=ag.scrollLeft(),aj=ae.treeObj.offset(),aD=ae.treeObj.get(0).scrollHeight,aT=ae.treeObj.get(0).scrollWidth,a2=(a6.clientY+av-aj.top),aP=(ae.treeObj.height()+aj.top-a6.clientY-av),aK=(a6.clientX+a4-aj.left),au=(ae.treeObj.width()+aj.left-a6.clientX-a4),ax=(a2<R.edit.drag.borderMax&&a2>R.edit.drag.borderMin),a7=(aP<R.edit.drag.borderMax&&aP>R.edit.drag.borderMin),aN=(aK<R.edit.drag.borderMax&&aK>R.edit.drag.borderMin),ar=(au<R.edit.drag.borderMax&&au>R.edit.drag.borderMin),ak=a2>R.edit.drag.borderMin&&aP>R.edit.drag.borderMin&&aK>R.edit.drag.borderMin&&au>R.edit.drag.borderMin,aH=(ax&&ae.treeObj.scrollTop()<=0),aG=(a7&&(ae.treeObj.scrollTop()+ae.treeObj.height()+10)>=aD),an=(aN&&ae.treeObj.scrollLeft()<=0),aB=(ar&&(ae.treeObj.scrollLeft()+ae.treeObj.width()+10)>=aT);if(a6.target&&v.isChildOrSelf(a6.target,ae.treeId)){var at=a6.target;while(at&&at.tagName&&!v.eqs(at.tagName,"li")&&at.id!=ae.treeId){at=at.parentNode}var aA=true;for(a0=0,aW=T.length;a0<aW;a0++){ay=T[a0];if(at.id===ay.tId){aA=false;break}else{if(i(ay,R).find("#"+at.id).length>0){aA=false;break}}}if(aA&&a6.target&&v.isChildOrSelf(a6.target,at.id+q.id.A)){ab=f(at);Q=at.id}}ay=T[0];if(ak&&v.isChildOrSelf(a6.target,ae.treeId)){if(!ab&&(a6.target.id==ae.treeId||aH||aG||an||aB)&&(ac||(!ac&&ay.parentTId))){ab=ae.treeObj}if(ax){ae.treeObj.scrollTop(ae.treeObj.scrollTop()-10)}else{if(a7){ae.treeObj.scrollTop(ae.treeObj.scrollTop()+10)}}if(aN){ae.treeObj.scrollLeft(ae.treeObj.scrollLeft()-10)}else{if(ar){ae.treeObj.scrollLeft(ae.treeObj.scrollLeft()+10)}}if(ab&&ab!=ae.treeObj&&ab.offset().left<ae.treeObj.offset().left){ae.treeObj.scrollLeft(ae.treeObj.scrollLeft()+ab.offset().left-ae.treeObj.offset().left)}}Z.css({top:(a6.clientY+av+3)+"px",left:(a6.clientX+a4+3)+"px"});var aF=0;var aE=0;if(ab&&ab.attr("id")!=ae.treeId){var aO=Q==null?null:A.getNodeCache(ae,Q),aI=((a6.ctrlKey||a6.metaKey)&&R.edit.drag.isMove&&R.edit.drag.isCopy)||(!R.edit.drag.isMove&&R.edit.drag.isCopy),ap=!!(D&&Q===D.tId),aM=!!(H&&Q===H.tId),aY=(ay.parentTId&&ay.parentTId==Q),aL=(aI||!aM)&&v.apply(ae.edit.drag.prev,[ae.treeId,T,aO],!!ae.edit.drag.prev),ao=(aI||!ap)&&v.apply(ae.edit.drag.next,[ae.treeId,T,aO],!!ae.edit.drag.next),ah=(aI||!aY)&&!(ae.data.keep.leaf&&!aO.isParent)&&v.apply(ae.edit.drag.inner,[ae.treeId,T,aO],!!ae.edit.drag.inner);function a3(){ab=null;Q="";E=q.move.TYPE_INNER;N.css({display:"none"});if(window.zTreeMoveTimer){clearTimeout(window.zTreeMoveTimer);window.zTreeMoveTargetNodeTId=null}}if(!aL&&!ao&&!ah){a3()}else{var aC=f("#"+Q+q.id.A,ab),aV=aO.isLastNode?null:f("#"+aO.getNextNode().tId+q.id.A,ab.next()),aX=aC.offset().top,aZ=aC.offset().left,aU=aL?(ah?0.25:(ao?0.5:1)):-1,aq=ao?(ah?0.75:(aL?0.5:0)):-1,am=(a6.clientY+av-aX)/aC.height();if((aU==1||am<=aU&&am>=-0.2)&&aL){aF=1-N.width();aE=aX-N.height()/2;E=q.move.TYPE_PREV}else{if((aq==0||am>=aq&&am<=1.2)&&ao){aF=1-N.width();aE=(aV==null||(aO.isParent&&aO.open))?(aX+aC.height()-N.height()/2):(aV.offset().top-N.height()/2);E=q.move.TYPE_NEXT}else{if(ah){aF=5-N.width();aE=aX;E=q.move.TYPE_INNER}else{a3()}}}if(ab){N.css({display:"block",top:aE+"px",left:(aZ+aF)+"px"});aC.addClass(q.node.TMPTARGET_NODE+"_"+E);if(U!=Q||G!=E){O=(new Date()).getTime()}if(aO&&aO.isParent&&E==q.move.TYPE_INNER){var az=true;if(window.zTreeMoveTimer&&window.zTreeMoveTargetNodeTId!==aO.tId){clearTimeout(window.zTreeMoveTimer);window.zTreeMoveTargetNodeTId=null}else{if(window.zTreeMoveTimer&&window.zTreeMoveTargetNodeTId===aO.tId){az=false}}if(az){window.zTreeMoveTimer=setTimeout(function(){if(E!=q.move.TYPE_INNER){return}if(aO&&aO.isParent&&!aO.open&&(new Date()).getTime()-O>ae.edit.drag.autoOpenTime&&v.apply(ae.callback.beforeDragOpen,[ae.treeId,aO],true)){j.switchNode(ae,aO);if(ae.edit.drag.autoExpandTrigger){ae.treeObj.trigger(q.event.EXPAND,[ae.treeId,aO])}}},ae.edit.drag.autoOpenTime+50);window.zTreeMoveTargetNodeTId=aO.tId}}}}}else{E=q.move.TYPE_INNER;if(ab&&v.apply(ae.edit.drag.inner,[ae.treeId,T,null],!!ae.edit.drag.inner)){ab.addClass(q.node.TMPTARGET_TREE)}else{ab=null}N.css({display:"none"});if(window.zTreeMoveTimer){clearTimeout(window.zTreeMoveTimer);window.zTreeMoveTargetNodeTId=null}}U=Q;G=E;R.treeObj.trigger(q.event.DRAGMOVE,[a6,R.treeId,T])}return false}ag.bind("mouseup",af);function af(ao){if(window.zTreeMoveTimer){clearTimeout(window.zTreeMoveTimer);window.zTreeMoveTargetNodeTId=null}U=null;G=null;ag.unbind("mousemove",P);ag.unbind("mouseup",af);ag.unbind("selectstart",F);M.css("cursor","auto");if(ab){ab.removeClass(q.node.TMPTARGET_TREE);if(Q){f("#"+Q+q.id.A,ab).removeClass(q.node.TMPTARGET_NODE+"_"+q.move.TYPE_PREV).removeClass(q.node.TMPTARGET_NODE+"_"+C.move.TYPE_NEXT).removeClass(q.node.TMPTARGET_NODE+"_"+C.move.TYPE_INNER)}}v.showIfameMask(R,false);L.showHoverDom=true;if(W.dragFlag==0){return}W.dragFlag=0;var am,ai,an;for(am=0,ai=T.length;am<ai;am++){an=T[am];if(an.isParent&&W.dragNodeShowBefore[an.tId]&&!an.open){j.expandCollapseNode(R,an,!an.open);delete W.dragNodeShowBefore[an.tId]}}if(Z){Z.remove()}if(N){N.remove()}var ah=((ao.ctrlKey||ao.metaKey)&&R.edit.drag.isMove&&R.edit.drag.isCopy)||(!R.edit.drag.isMove&&R.edit.drag.isCopy);if(!ah&&ab&&Q&&T[0].parentTId&&Q==T[0].parentTId&&E==q.move.TYPE_INNER){ab=null}if(ab){var aj=Q==null?null:A.getNodeCache(ae,Q);if(v.apply(R.callback.beforeDrop,[ae.treeId,T,aj,E,ah],true)==false){j.selectNodes(I,T);return}var ak=ah?v.clone(T):T;function al(){if(ac){if(!ah){for(var ar=0,aq=T.length;ar<aq;ar++){j.removeNode(R,T[ar])}}if(E==q.move.TYPE_INNER){j.addNodes(ae,aj,-1,ak)}else{j.addNodes(ae,aj.getParentNode(),E==q.move.TYPE_PREV?aj.getIndex():aj.getIndex()+1,ak)}}else{if(ah&&E==q.move.TYPE_INNER){j.addNodes(ae,aj,-1,ak)}else{if(ah){j.addNodes(ae,aj.getParentNode(),E==q.move.TYPE_PREV?aj.getIndex():aj.getIndex()+1,ak)}else{if(E!=q.move.TYPE_NEXT){for(ar=0,aq=ak.length;ar<aq;ar++){j.moveNode(ae,aj,ak[ar],E,false)}}else{for(ar=-1,aq=ak.length-1;ar<aq;aq--){j.moveNode(ae,aj,ak[aq],E,false)}}}}}j.selectNodes(ae,ak);var ap=i(ak[0],R).get(0);j.scrollIntoView(ap);R.treeObj.trigger(q.event.DROP,[ao,ae.treeId,ak,aj,E,ah])}if(E==q.move.TYPE_INNER&&v.canAsync(ae,aj)){j.asyncNode(ae,aj,false,al)}else{al()}}else{j.selectNodes(I,T);R.treeObj.trigger(q.event.DROP,[ao,R.treeId,T,null,null,null])}}ag.bind("selectstart",F);function F(){return false}if(S.preventDefault){S.preventDefault()}return true}},h={getAbs:function(E){var G=E.getBoundingClientRect(),D=document.body.scrollTop+document.documentElement.scrollTop,F=document.body.scrollLeft+document.documentElement.scrollLeft;return[G.left+F,G.top+D]},inputFocus:function(D){if(D.get(0)){D.focus();v.setCursorPosition(D.get(0),D.val().length)}},inputSelect:function(D){if(D.get(0)){D.focus();D.select()}},setCursorPosition:function(E,F){if(E.setSelectionRange){E.focus();E.setSelectionRange(F,F)}else{if(E.createTextRange){var D=E.createTextRange();D.collapse(true);D.moveEnd("character",F);D.moveStart("character",F);D.select()}}},showIfameMask:function(K,I){var H=A.getRoot(K);while(H.dragMaskList.length>0){H.dragMaskList[0].remove();H.dragMaskList.shift()}if(I){var L=i("iframe",K);for(var G=0,E=L.length;G<E;G++){var F=L.get(G),D=v.getAbs(F),J=i("<div id='zTreeMask_"+G+"' class='zTreeMask' style='top:"+D[1]+"px; left:"+D[0]+"px; width:"+F.offsetWidth+"px; height:"+F.offsetHeight+"px;'></div>",K);J.appendTo(i("body",K));H.dragMaskList.push(J)}}}},d={addEditBtn:function(E,F){if(F.editNameFlag||i(F,q.id.EDIT,E).length>0){return}if(!v.apply(E.edit.showRenameBtn,[E.treeId,F],E.edit.showRenameBtn)){return}var G=i(F,q.id.A,E),D="<span class='"+q.className.BUTTON+" edit' id='"+F.tId+q.id.EDIT+"' title='"+v.apply(E.edit.renameTitle,[E.treeId,F],E.edit.renameTitle)+"' treeNode"+q.id.EDIT+" style='display:none;'></span>";G.append(D);i(F,q.id.EDIT,E).bind("click",function(){if(!v.uCanDo(E)||v.apply(E.callback.beforeEditName,[E.treeId,F],true)==false){return false}j.editNode(E,F);return false}).show()},addRemoveBtn:function(D,E){if(E.editNameFlag||i(E,q.id.REMOVE,D).length>0){return}if(!v.apply(D.edit.showRemoveBtn,[D.treeId,E],D.edit.showRemoveBtn)){return}var G=i(E,q.id.A,D),F="<span class='"+q.className.BUTTON+" remove' id='"+E.tId+q.id.REMOVE+"' title='"+v.apply(D.edit.removeTitle,[D.treeId,E],D.edit.removeTitle)+"' treeNode"+q.id.REMOVE+" style='display:none;'></span>";G.append(F);i(E,q.id.REMOVE,D).bind("click",function(){if(!v.uCanDo(D)||v.apply(D.callback.beforeRemove,[D.treeId,E],true)==false){return false}j.removeNode(D,E);D.treeObj.trigger(q.event.REMOVE,[D.treeId,E]);return false}).bind("mousedown",function(H){return true}).show()},addHoverDom:function(D,E){if(A.getRoots().showHoverDom){E.isHover=true;if(D.edit.enable){j.addEditBtn(D,E);j.addRemoveBtn(D,E)}v.apply(D.view.addHoverDom,[D.treeId,E])}},cancelCurEditNode:function(K,L,H){var J=A.getRoot(K),G=K.data.key.name,E=J.curEditNode;if(E){var F=J.curEditInput,I=L?L:(H?E[G]:F.val());if(v.apply(K.callback.beforeRename,[K.treeId,E,I,H],true)===false){return false}E[G]=I;var D=i(E,q.id.A,K);D.removeClass(q.node.CURSELECTED_EDIT);F.unbind();j.setNodeName(K,E);E.editNameFlag=false;J.curEditNode=null;J.curEditInput=null;j.selectNode(K,E,false);K.treeObj.trigger(q.event.RENAME,[K.treeId,E,H])}J.noSelection=true;return true},editNode:function(G,H){var D=A.getRoot(G);j.editNodeBlur=false;if(A.isSelectedNode(G,H)&&D.curEditNode==H&&H.editNameFlag){setTimeout(function(){v.inputFocus(D.curEditInput)},0);return}var F=G.data.key.name;H.editNameFlag=true;j.removeTreeDom(G,H);j.cancelCurEditNode(G);j.selectNode(G,H,false);i(H,q.id.SPAN,G).html("<input type=text class='rename' id='"+H.tId+q.id.INPUT+"' treeNode"+q.id.INPUT+" >");var E=i(H,q.id.INPUT,G);E.attr("value",H[F]);if(G.edit.editNameSelectAll){v.inputSelect(E)}else{v.inputFocus(E)}E.bind("blur",function(I){if(!j.editNodeBlur){j.cancelCurEditNode(G)}}).bind("keydown",function(I){if(I.keyCode=="13"){j.editNodeBlur=true;j.cancelCurEditNode(G)}else{if(I.keyCode=="27"){j.cancelCurEditNode(G,null,true)}}}).bind("click",function(I){return false}).bind("dblclick",function(I){return false});i(H,q.id.A,G).addClass(q.node.CURSELECTED_EDIT);D.curEditInput=E;D.noSelection=false;D.curEditNode=H},moveNode:function(N,G,Q,F,ab,H){var S=A.getRoot(N),L=N.data.key.children;if(G==Q){return}if(N.data.keep.leaf&&G&&!G.isParent&&F==q.move.TYPE_INNER){return}var V=(Q.parentTId?Q.getParentNode():S),P=(G===null||G==S);if(P&&G===null){G=S}if(P){F=q.move.TYPE_INNER}var D=(G.parentTId?G.getParentNode():S);if(F!=q.move.TYPE_PREV&&F!=q.move.TYPE_NEXT){F=q.move.TYPE_INNER}if(F==q.move.TYPE_INNER){if(P){Q.parentTId=null}else{if(!G.isParent){G.isParent=true;G.open=!!G.open;j.setNodeLineIcos(N,G)}Q.parentTId=G.tId}}var I,K;if(P){I=N.treeObj;K=I}else{if(!H&&F==q.move.TYPE_INNER){j.expandCollapseNode(N,G,true,false)}else{if(!H){j.expandCollapseNode(N,G.getParentNode(),true,false)}}I=i(G,N);K=i(G,q.id.UL,N);if(!!I.get(0)&&!K.get(0)){var Z=[];j.makeUlHtml(N,G,Z,"");I.append(Z.join(""))}K=i(G,q.id.UL,N)}var X=i(Q,N);if(!X.get(0)){X=j.appendNodes(N,Q.level,[Q],null,-1,false,true).join("")}else{if(!I.get(0)){X.remove()}}if(K.get(0)&&F==q.move.TYPE_INNER){K.append(X)}else{if(I.get(0)&&F==q.move.TYPE_PREV){I.before(X)}else{if(I.get(0)&&F==q.move.TYPE_NEXT){I.after(X)}}}var U,T,J=-1,W=0,aa=null,E=null,Y=Q.level;if(Q.isFirstNode){J=0;if(V[L].length>1){aa=V[L][1];aa.isFirstNode=true}}else{if(Q.isLastNode){J=V[L].length-1;aa=V[L][J-1];aa.isLastNode=true}else{for(U=0,T=V[L].length;U<T;U++){if(V[L][U].tId==Q.tId){J=U;break}}}}if(J>=0){V[L].splice(J,1)}if(F!=q.move.TYPE_INNER){for(U=0,T=D[L].length;U<T;U++){if(D[L][U].tId==G.tId){W=U}}}if(F==q.move.TYPE_INNER){if(!G[L]){G[L]=new Array()}if(G[L].length>0){E=G[L][G[L].length-1];E.isLastNode=false}G[L].splice(G[L].length,0,Q);Q.isLastNode=true;Q.isFirstNode=(G[L].length==1)}else{if(G.isFirstNode&&F==q.move.TYPE_PREV){D[L].splice(W,0,Q);E=G;E.isFirstNode=false;Q.parentTId=G.parentTId;Q.isFirstNode=true;Q.isLastNode=false}else{if(G.isLastNode&&F==q.move.TYPE_NEXT){D[L].splice(W+1,0,Q);E=G;E.isLastNode=false;Q.parentTId=G.parentTId;Q.isFirstNode=false;Q.isLastNode=true}else{if(F==q.move.TYPE_PREV){D[L].splice(W,0,Q)}else{D[L].splice(W+1,0,Q)}Q.parentTId=G.parentTId;Q.isFirstNode=false;Q.isLastNode=false}}}A.fixPIdKeyValue(N,Q);A.setSonNodeLevel(N,Q.getParentNode(),Q);j.setNodeLineIcos(N,Q);j.repairNodeLevelClass(N,Q,Y);if(!N.data.keep.parent&&V[L].length<1){V.isParent=false;V.open=false;var O=i(V,q.id.UL,N),R=i(V,q.id.SWITCH,N),M=i(V,q.id.ICON,N);j.replaceSwitchClass(V,R,q.folder.DOCU);j.replaceIcoClass(V,M,q.folder.DOCU);O.css("display","none")}else{if(aa){j.setNodeLineIcos(N,aa)}}if(E){j.setNodeLineIcos(N,E)}if(!!N.check&&N.check.enable&&j.repairChkClass){j.repairChkClass(N,V);j.repairParentChkClassWithSelf(N,V);if(V!=Q.parent){j.repairParentChkClassWithSelf(N,Q)}}if(!H){j.expandCollapseParentNode(N,Q.getParentNode(),true,ab)}},removeEditBtn:function(D,E){i(E,q.id.EDIT,D).unbind().remove()},removeRemoveBtn:function(D,E){i(E,q.id.REMOVE,D).unbind().remove()},removeTreeDom:function(D,E){E.isHover=false;j.removeEditBtn(D,E);j.removeRemoveBtn(D,E);v.apply(D.view.removeHoverDom,[D.treeId,E])},repairNodeLevelClass:function(E,G,F){if(F===G.level){return}var H=i(G,E),K=i(G,q.id.A,E),J=i(G,q.id.UL,E),D=q.className.LEVEL+F,I=q.className.LEVEL+G.level;H.removeClass(D);H.addClass(I);K.removeClass(D);K.addClass(I);J.removeClass(D);J.addClass(I)},selectNodes:function(G,E){for(var F=0,D=E.length;F<D;F++){j.selectNode(G,E[F],F>0)}}},t={tools:h,view:d,event:g,data:p};f.extend(true,f.fn.zTree.consts,C);f.extend(true,f.fn.zTree._z,t);var b=f.fn.zTree,v=b._z.tools,q=b.consts,j=b._z.view,A=b._z.data,s=b._z.event,i=v.$;A.exSetting(m);A.addInitBind(n);A.addInitUnBind(z);A.addInitCache(c);A.addInitNode(w);A.addInitProxy(o);A.addInitRoot(x);A.addZTreeTools(k);var u=j.cancelPreSelectedNode;j.cancelPreSelectedNode=function(F,G){var H=A.getRoot(F).curSelectedList;for(var E=0,D=H.length;E<D;E++){if(!G||G===H[E]){j.removeTreeDom(F,H[E]);if(G){break}}}if(u){u.apply(j,arguments)}};var y=j.createNodes;j.createNodes=function(G,H,E,D,F){if(y){y.apply(j,arguments)}if(!E){return}if(j.repairParentChkClassWithSelf){j.repairParentChkClassWithSelf(G,D)}};var r=j.makeNodeUrl;j.makeNodeUrl=function(D,E){return D.edit.enable?null:(r.apply(j,arguments))};var a=j.removeNode;j.removeNode=function(E,F){var D=A.getRoot(E);if(D.curEditNode===F){D.curEditNode=null}if(a){a.apply(j,arguments)}};var B=j.selectNode;j.selectNode=function(F,G,E){var D=A.getRoot(F);if(A.isSelectedNode(F,G)&&D.curEditNode==G&&G.editNameFlag){return false}if(B){B.apply(j,arguments)}j.addHoverDom(F,G);return true};var e=v.uCanDo;v.uCanDo=function(E,F){var D=A.getRoot(E);if(F&&(v.eqs(F.type,"mouseover")||v.eqs(F.type,"mouseout")||v.eqs(F.type,"mousedown")||v.eqs(F.type,"mouseup"))){return true}if(D.curEditNode){j.editNodeBlur=false;D.curEditInput.focus()}return(!D.curEditNode)&&(e?e.apply(j,arguments):true)}})(jQuery);