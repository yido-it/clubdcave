/**
 * Created by KIMKT on 2018-08-18.
 */
( function() {
    CKEDITOR.plugins.add( 'img-upload',
        {
            lang: ['en'],
            init: function( editor )
            {
            	var url = editor.config.imgUploadUrl;
                var count = 0;
                var $placeholder = $("<div></div>").css({
                    position: 'absolute', bottom: 0, left: 0, right: 0, backgroundColor: "rgba(20, 20, 20, .6)", padding: 5, color: "#fff"
                }).hide();


                var checkRequirement = function(condition, message) {
                    if (!condition)
                        throw Error("Assert failed" + (typeof message !== "undefined" ? ": " + message : ""));
                };


                function doNothing(e) { }
                function orPopError(err) { alert(err); }

                function insertImage(href) {
                    var filter = "win16|win32|win64|mac";
                    var width = 0;

                    if(navigator.platform){
                    	if(0 > filter.indexOf(navigator.platform.toLowerCase())){
                    		width = window.screen.availWidth-100;
   	                   	}else{
   	                   		width = window.innerWidth-100;
       	                }
                    }
                	
                    var img = new Image();
                    img.onload = function() {
                        if(parseInt(img.width) < width) {
                            width = img.width;
                        } else {
                            width: width;
                        }
                        var elem = editor.document.createElement('img', {
                            attributes: {
                                src: href,
                                width: width
                            }
                        });
                        editor.insertElement(elem);
                    };
                    img.src = href;
                }

                function validateUploads(files) {
                    var pass = true;
                    $.each(files, function(i, file) {
                        var name = file.name;
                        var extension = name.substring(name.lastIndexOf('.') + 1).toLowerCase();
                        if (extension !== "gif" && extension !== "png" && extension !== "bmp"
                            && extension !== "jpeg" && extension !== "jpg") {
                            pass = false;
                        }
                    });
                    return pass;
                }

                function upload(file, filename) {
                	var data = new FormData();
                	data.append("file",file,filename);
                    return new Promise(function (resolve, reject) {
                    	$.ajax({
                    		type: 'POST',
    						url: url,
    						contentType: false,
    					    processData: false,
    						data: data,
    						success: function(data){
    							console.log(data);
    							insertImage(data);
    						},
    						fail: function(e){
    							alert("error - " + e);
    						}
                    	});
                    });
                };

                function dataURLtoFile(dataurl) {
                    var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
                        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
                    
                    while(n--){
                        u8arr[n] = bstr.charCodeAt(n);
                    }
                    //return new File([u8arr], filename, {type:mime});
                    var b = new Blob([u8arr], {type:mime});
                    return b;
                }


                function dropHandler(e) {
                    e.preventDefault();
                    files = e.dataTransfer.files;
                    if(!validateUploads(files)) {
                        alert("이미지 파일이 아닙니다.");
                        return;
                    } else {
                        $placeholder.show();
                        $.each(files, function(i, file) {
                            canvasResize(file, {
                                width: 720,
                                height: 0,
                                crop: false,
                                quality: 80,
                                //rotate: 90,
                                callback: function (data, width, height) {
                                    upload(dataURLtoFile(data),file.name).then(insertImage, orPopError);
                                }
                            });

                        });
                    }
                    $placeholder.text("이미지가 업로드 되었습니다.").fadeIn();
                }


                editor.on("instanceReady", function () {
                    // setup progress indicator
                    var $w = $(editor.window.getFrame().$).parent();
                    $w.css({ position:'relative' });
                    $placeholder.appendTo($w);

                    // drag and drop
                    var iframeBase = document.querySelector('iframe').contentDocument.querySelector('html');
                    var iframeBody = iframeBase.querySelector('body');
                    iframeBody.ondragover = doNothing;
                    iframeBody.ondrop = dropHandler;

                    paddingToCenterBody = ((iframeBase.offsetWidth - iframeBody.offsetWidth) / 2) + 'px';
                    iframeBase.style.height = '100%';
                    iframeBase.style.width = '100%';
                    iframeBase.style.overflowX = 'hidden';

                    iframeBody.style.height = '100%';
                    iframeBody.style.margin = '0';
                    iframeBody.style.paddingLeft = paddingToCenterBody;
                    iframeBody.style.paddingRight = paddingToCenterBody;
                });

                editor.ui.addButton( 'ImgUpload',
                    {
                        label : '이미지 업로드',
                        toolbar : 'insert',
                        command : 'img-upload',
                        icon : this.path + 'icons/icon.png'
                    });

                editor.addCommand( 'img-upload', {
                    exec: function(){
                        console.log("first");
                        $input = $('<input type="file" multiple/>');
                        $input.on("change", function (e) {
                            files = e.target.files;
                            if(!validateUploads(files)) {
                            	alert("이미지 파일이 아닙니다.");
                                return;
                            } else {
                                $.each(files, function(i, file) {
                                    canvasResize(file, {
                                        width: 720,
                                        height: 0,
                                        crop: false,
                                        quality: 80,
                                        //rotate: 90,
                                        callback: function (data, width, height) {
                                            upload(dataURLtoFile(data),file.name).then(insertImage, orPopError);
                                        }
                                    });
                                });
                            }
                               $placeholder.text("이미지가 업로드 되었습니다.").fadeIn();
                        });
                        $input.click();
                    }
                });
            }
        });
})();

