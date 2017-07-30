<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="jquery-3.2.1.min.js"></script>
        <link rel = "stylesheet" href = "stylesheet.css">
        <style type="text/css">
            @import url('https://fonts.googleapis.com/css?family=Open+Sans');
            @import url('https://fonts.googleapis.com/css?family=Oswald');
            
            form button {
                border-radius: 15px;
                height: 30px;
                position: absolute;
                    top: 20px;

                right: 0;
                width: 2em;
                background-color: transparent;
                background-repeat:no-repeat;
                border: none;
                cursor:pointer;
                overflow: hidden;
                outline:none;
                margin-right: 5px;
                color: darkslategrey;
            }
        </style>
        
        </script>
        <script>
            var my_photos_cnt = 0;
            var shared_photos_cnt = 0;
            var numViewPhoto = 5;
            
            function showPhoto(photoObj) {
                var a_photo = document.createElement("a");

                var new_div_tab_photo = document.createElement("div");
                new_div_tab_photo.className += "tab_photo";

                var new_tab_title = document.createElement("div");
                new_tab_title.className += "tab_title";
                new_tab_title.textContent = photoObj.title;

                // if photo is private and is owned by current user
                var new_tab_privacy = document.createElement("div");
                new_tab_privacy.className += "tab_privacy";
                new_tab_privacy.innerHTML = "<i class=\"fa fa-eye-slash\" aria-hidden=\"true\"></i>";

                //thumbnails
                var new_thumb = document.createElement("img");
                new_thumb.className += "thumbnail";
                //new_thumb.setAttribute("src", photoObuserpage.htmlthumbnailUrl);
                new_thumb.setAttribute("src", photoObj.filepath);
                a_photo.textContent = "";
                a_photo.href = "#" + photoObj.photoId;

                // add thumbnail to div (image)
                new_div_tab_photo.appendChild(new_thumb);
                new_div_tab_photo.appendChild(new_tab_title);
                new_div_tab_photo.appendChild(new_tab_privacy);

                // add link to div
                a_photo.appendChild(new_div_tab_photo);
                
                $("#tab_con").append(a_photo);
                $(new_div_tab_photo).show(500);
            }

            function showSharedPhoto(photoObj) {
                var a_photo = document.createElement("a");

                var new_div_tab_photo = document.createElement("div");
                new_div_tab_photo.className += "tab_photo";

                var new_tab_title = document.createElement("div");
                new_tab_title.className += "tab_title";
                new_tab_title.textContent = "hello";

                // if photo is private and is shared to current user
                var new_tab_privacy = document.createElement("div");
                new_tab_privacy.className += "tab_privacy";
                new_tab_privacy.innerHTML = "<i class=\"fa fa-eye\" aria-hidden=\"true\"></i>";

                //thumbnails
                var new_thumb = document.createElement("img");
                new_thumb.className += "thumbnail";
                //new_thumb.setAttribute("src", photoObuserpage.htmlthumbnailUrl);
                new_thumb.setAttribute("src", "sv.png");
                a_photo.textContent = "";
                a_photo.href = "#" + photoObj.id;

                // add thumbnail to div (image)
                new_div_tab_photo.appendChild(new_thumb);
                new_div_tab_photo.appendChild(new_tab_title);
                new_div_tab_photo.appendChild(new_tab_privacy);

                // add link to div
                a_photo.appendChild(new_div_tab_photo);
                
                $("#tab_con").append(a_photo);
                $(new_div_tab_photo).show(500);
            }

            function getPhotos(){
            	myPhotoList = ${myPhotoList};
            	var limit = my_photos_cnt + numViewPhoto;
            	for(i = my_photos_cnt; i < myPhotoList.length && i < limit; i++) {
            		showPhoto(myPhotoList[i]);
            		my_photos_cnt++;
            	}
            	
                if (my_photos_cnt >= myPhotoList.length) {
                    $("#viewmore").hide();
                }
            }

            function getSharedPhotos(){
                $.ajax({
                    url: root + '/photos/'
                    , method: 'GET'
                }).then(function (photos) {
                    for (i = photos.length - photos_cnt - 1; i > photos.length - photos_cnt - numViewPhoto - 1 && i >= 0; i--) {
                        showSharedPhoto(photos[i]);
                        console.log(photos[i].id);
                    }
                    photos_cnt = photos_cnt + numViewPhoto;
                    if (photos_cnt >= photos.length) {
                        $("#viewmore").hide();
                    }
                });
            }

            function showImgModal() {
                console.log("show the modal thing");
                //console.log($(this).data());
				myPhotoList = ${myPhotoList};
				var id = parseInt(window.location.hash.slice(1));
            	p = findPhoto(myPhotoList, id);
            	
                //get value of id from the child div (which is id div)
                var modal_thing = document.getElementById("myModal");
                var modal_img = document.getElementById("modalimg");
                var modal_caption = document.getElementById("caption");
                var modal_uploader = document.getElementById("imguploader");
                var modal_album = document.getElementById("imgalbum");
                var modal_description = document.getElementById("imgdescription");
                
                $(modal_album).empty();
                $(modal_img).empty();
                $(modal_uploader).empty();
                $(modal_description).empty();
                
                var a_user = document.createElement("a");
                var a_album = document.createElement("a");

                

                document.title = p.title + " | Photos | Oink";
                modal_caption.innerHTML = p.title;
                modal_img.src = p.filepath;
                modal_description.innerHTML = p.description;
                //a_user.href = "userpage.html?id=" + p.username + "#posts";
                a_user.textContent = p.username;
                modal_uploader.appendChild(a_user);
                
                $("#imgcontainer").empty();
                var new_img_tag = [];
                for(i = 0; i < p.tags.length; i++) {
	                new_img_tag.push(document.createElement("div"));
	                new_img_tag[i].className += "imgtag";
	                new_img_tag[i].textContent = "#" + p.tags[i];
	                $("#imgcontainer").append(new_img_tag[i]);
                }
                modal_thing.style.display = "table";
            }
            
            function hideUserPosts() {
                $( '#tab_con a' ).each( function() {
                    var $a_photo   = $( this ),
                    $div_photo   = $a_photo.next( 'div' );
                    console.log('hideUserPosts');
                    console.log($a_photo.attr("id"));

                    if($a_photo.attr("id") != "add-photo") {
                        $a_photo.remove();
                    } else {
                        $a_photo.css("display", "none");
                    }
                    
                });
            }

            function closeModal() {
                var modal = document.getElementById("myModal");
                var modallog = document.getElementById("modal-login");
                var modalap = document.getElementById("modal-add-photo");
                var modalreg = document.getElementById("modal-reg");

                modal.style.display = 'none';
                modallog.style.display = 'none';
                modalap.style.display = 'none';
                modalreg.style.display = 'none';

                history.pushState('', document.title, window.location.pathname);
            }

            $(document).ready(function(){
                
            	var role = "${sessionScope.role}";
            	var action = "${action}";
            	console.log(role);
            	console.log("ACTION: ${action}");
            	
            	// adjust UI depending on the role
            	if(role == "user") {
            		console.log("I AM AN USER")
            		console.log("${sessionScope.sUsername} ");
            		$("#hlink_login").hide();
            		$("#hlink_reg").hide();
            		$("#hlink_logout").show();
                	getPhotos();
                	var username = "${sessionScope.sUsername} ";
                	var description = "${sessionScope.sDescription} ";
                	// you can hide the log in and register and show logout and account
                	
                	// you can show the section for private photos
            	}
            	else {
            		$("#hlink_login").show();
            		$("#hlink_reg").show();
            		$("#hlink_logout").hide();
	            	// check for action failures
	            	if(action == "login") {
	            		// if login failed
	                	if("${ERROR}" == "failed") {
	                		console.log("I AM STILL A GUEST")
	                		console.log("LOGIN FAILED");
	                		
	                    	// continue to show the modal of login but add a div inside to state that the username or password is incorrect
	                    	
	                	}
	            	}
	            	
	            	else if(action == "register") {
	            		// if register failed
	                	if("${ERROR}" == "failed") {
	                		console.log("I AM STILL A GUEST")
	                		console.log("REGISTER FAILED");
	                		
	                    	// continue to show the modal of register but add a div inside to state that the username or password is incorrect
	                	}
	            	}
	            	var username = "${sessionScope.sUsername} ";
                	var description = "${sessionScope.sDescription} ";
                	console.log("USERNAME SESSION: " + username);
	            	getPhotos();
            	}


                $(document).on("mouseenter", ".imgtag", function(){
                    var $img_span = $(this).find("span");
                    var $img_i = ($img_span).find("i");
                    var $img_i_plus = ($img_span).find("i.fa-plus");

                    console.log("INPUT LENGTH " + $(this).find("input").length);
                    if($img_span.attr("id") == "add-tag" && $(this).find("input").length == 0) {
                        $img_span.stop().hide();
                        $img_span.append("<span>Add tag</span>");
                        $img_span.css({marginLeft:"10px"});
                        $img_span.stop().show(250);
                    } else if($img_i.length == 0 || $img_i_plus.length == 1) {
                        $img_span.stop().hide();
                        $img_span.append("<i class=\"fa fa-times\" aria-hidden=\"true\"></i>");
                        $img_span.css({marginLeft:"10px"});
                        $img_span.stop().show(250);
                    }
                });


                $(document).on("mouseleave", ".imgtag", function(){
                    var $img_span = $(this).find("span");
                    $img_span.stop().hide(250, function(){
                        $img_span.text("");
                        $img_span.css({marginLeft:"0px"});
                    });
                });



                $(document).on("click", ".fa-times", function() {
                    alert("REMOVE TAG");
                });



                $(document).on("click", "#addtag", function() {
                    var $img_span = $(this).find("span");
                    var new_textarea = document.createElement("input");

                    new_textarea.type="text";
                    new_textarea.placeholder="Enter tag";
                    new_textarea.size="8";

                    //$(this).css("pointerEvents", "none");
                    if($(this).find("input").length == 0) {
                        console.log("ADD INPUT FIELD");
                        $(this).append(new_textarea);
                        $($img_span).remove( ":contains('Add tag')" );
                    }
                    //alert("ADD TAG");
                });

                $(document).on("click", ".fa-plus", function(e) {
                    e.stopPropagation();
                    if($("#addtag").find("input").length >= 1) {
                        
                        $("#addtag").empty();
                        alert("ADD TAG TO DB");
                        $("#addtag").append("<i class=\"fa fa-plus\" aria-hidden=\"true\"></i><span id=\"add-tag\"></span>");

                    }
                });


                // Changes the contents of the usermain div depending on the hash selected
                $(window).on('hashchange',function(){
                    if(window.location.hash.slice(1) == ""){
                        console.log("load posts");
                        closeModal();
                        getPhotos();
                        $("#viewmore").show();
                        $("#add-photo").css("display", "inline-block");
                    } 
                    /*else if(window.location.hash.slice(1) == "shared") {
                        $('a#nav-current').removeAttr('id');
                        var navlinklist = document.getElementsByClassName("navlink");
                        hideUserPosts();
                        getSharedPhotos();
                        for(i = 0; i < navlinklist.length; i++) {
                            console.log(navlinklist[i].text)
                            if(navlinklist[i].text == "Shared with me") {
                                navlinklist[i].id += "nav-current";
                            }
                        }
                    } 
                    */else if(window.location.hash.slice(1) != "") {
                        showImgModal();
                    }
                });



                // close modal on x button
                $(document).on("click", ".close", function(){
                    console.log("close modal");
                    window.location.hash = "";
                    document.title = "Oink";
                    
                    closeModal();
                });
                
                // close modal after any click outside modal
                $(window).on("click", function() {
                    

                    var modal = document.getElementById("myModal");
                    var modallog = document.getElementById("modal-login");
                    var modalap = document.getElementById("modal-add-photo");

                    var modalc = document.getElementById("modal-container");
                    var modalc2 = document.getElementById("modal-container-2");
                    var modalclog = document.getElementById("modal-login-container");
                    var modalc2log = document.getElementById("modal-login-container-2");
                    var modaladdphoto = document.getElementById("modal-add-photo-container");
                    var modaladdphoto2 = document.getElementById("modal-add-photo-container-2");
                    var modalcreg = document.getElementById("modal-reg-container");
                    var modalcreg2 = document.getElementById("modal-reg-container-2");

                    if(event.target == modalc2 || event.target == modalc || event.target == modallog || event.target == modalclog || event.target == modalc2log || event.target == modaladdphoto || event.target == modaladdphoto2 || event.target == modalcreg || event.target == modalcreg2) {
                        closeModal();
                        window.location.hash = "";
                        document.title = "Oink";
                    }

                    console.log("onclick: " + event.target);
                });



                // Log in and register
                $('a.hlink').click(function(){
                    console.log(event.target.text);
                    event.preventDefault(); 
                    var hlinklist = document.getElementsByClassName("hlink");
                    for(i = 0; i < hlinklist.length; i++) {
                        if(event.target.text == hlinklist[i].text && hlinklist[i].text == "Login") {
                            var modallog = document.getElementById("modal-login");
                            modallog.style.display = 'table';
                        }
                        if(event.target.text == hlinklist[i].text && hlinklist[i].text == "Register") {
                            var modalreg = document.getElementById("modal-reg");
                            modalreg.style.display = 'table';
                        }
                        if(event.target.text == hlinklist[i].text && hlinklist[i].text == "Logout") {
                        	window.location.href="logout";
                        }
                    }
                    return false; 
                });



                // Add photo
                $('#add-photo').click(function(){
                    console.log("add photo event");
                    event.preventDefault();
                    var modallog = document.getElementById("modal-add-photo");
                    modallog.style.display = 'table';
                });



                // Add image file
                $( '.pic-input' ).each( function() {
                    var $input   = $( this ),

                    $label   = $input.next( 'label' ),
                    labelVal = $label.html();
                    console.log('pic-input each');

                    $input.on( 'change', function( e ) {
                        console.log('pic-input change');
                        var fileName = '';
                        console.log(this.files);
                        console.log(e.target.value);
                        console.log(e.target.value.split( '\\' ).pop());

                        if( this.files && this.files.length > 1 )
                            fileName = ( this.getAttribute( 'data-multiple-caption' ) || '' ).replace( '{count}', this.files.length );
                        else if( e.target.value )
                            fileName = e.target.value.split( '\\' ).pop();

                        if( fileName ) {
                            $label.find( 'span' ).html( "<i class=\"fa fa-check-circle\" aria-hidden=\"true\"></i> " + fileName );
                            //$label.css('color', 'gray');
                        }
                        else
                            $label.html( labelVal );
                    });

                    // Firefox bug fix
                    $input
                    .on( 'focus', function(){ $input.addClass( 'has-focus' ); })
                    .on( 'blur', function(){ $input.removeClass( 'has-focus' ); });
                });



                // Add allowed users
                $( '.pic-security').each( function() {
                    var $input   = $( this ),
                    $label   = $input.next( 'label' ),
                    labelVal = $label.html();
                    console.log('pic-security each');

                    
                    var modal = document.getElementById("pic-allowed-user");

                    $input.on( 'change', function( e ) {

                        console.log('pic-security change');
                        if(labelVal == "Public") {
                            console.log("Public radio button selected");
                            modal.style.display = 'none';
                        } else if(labelVal == "Private") {
                            console.log("Private radio button selected");
                            modal.style.display = 'block';
                        }

                    });
                })

                $(document).on('submit','#form-reg',function(){
                    
                    console.log("SUBMIT REGISTER");
                    console.log($(this).find("input"));

                    var user_regex = /^[0-9a-zA-Z]{3,}$/; // at least 3 characters alphanumeric
                    var pass_regex = /^[0-9a-zA-Z]{8,}$/; // at least 8 characters alphanumeric

                    var $p_errors = $(this).find("p");
                    var $input_user = $(this).find("input")[0];
                    var $input_pass = $(this).find("input")[1];
                    var $input_pass_c = $(this).find("input")[2];
                    var $input_desc = $(this).find("textarea")[0];

                    if(user_regex.test($input_user.value)) {
                        console.log("username 3 char alphanumeric pass");
                        $($input_user).css('box-shadow', 'none');
                        $($p_errors[0]).css('display', 'none');
                    } else {
                        console.log("username 3 char alphanumeric fail");
                        event.preventDefault(); 
                        $($p_errors[0]).css('display', 'block');
                        $($input_user).css('box-shadow', '0 0 0 1pt rgba(252, 45, 45, 0.8)');
                    }

                    if(pass_regex.test($input_pass.value)) {
                        console.log("password 8 char alphanumeric pass");
                        $($input_pass).css('box-shadow', 'none');
                        $($p_errors[1]).css('display', 'nones');
                        if(($input_pass.value == $input_pass_c.value)) {
                            console.log("password match");
                            $($p_errors[2]).css('display', 'none');
                            $($input_pass).css('box-shadow', 'none');
                            $($input_pass_c).css('box-shadow', 'none');
                        } else {
                            console.log("passwords do not match");
                            event.preventDefault();
                            $($p_errors[2]).css('display', 'block');
                            $($input_pass).css('box-shadow', '0 0 0 1pt rgba(252, 45, 45, 0.8)');
                            $($input_pass_c).css('box-shadow', '0 0 0 1pt rgba(252, 45, 45, 0.8)');
                        }
                    } else {
                        console.log("password 8 char alphanumeric fail");
                        event.preventDefault(); 
                        $($input_pass).css('box-shadow', '0 0 0 1pt rgba(252, 45, 45, 0.8)');
                        $($input_pass_c).css('box-shadow', '0 0 0 1pt rgba(252, 45, 45, 0.8)');
                        $($p_errors[1]).css('display', 'block');
                    }


                });
                
            });

        </script>
    </head>

    <body>
        <div id="header">
            <div id="hleft">
                <a id="hlogo" href="mainpage">OINK</a>
            </div>
            <div id="hright">
                <form action="search" method="get" class="index-search-form" name="">
                    <input name="keyword" type="text" placeholder="What are you looking for?">
                    <button class="" type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>
                </form>
                <!-- <a class="hlink" href="index.html">Search</a> -->
                <!-- <a class="hlink" href="photos.html">Photos</a> -->
                <a class="hlink" id="hlink_login" href="index.jsp">Login</a> 
                <a class="hlink" id="hlink_reg" href="index.jsp">Register</a>
                <a class="hlink" id="hlink_logout" href="logout">Logout</a> 
            </div>
        </div>

        <div id="bodypanel">
            <div id="usermaindiv">
                <div id="userinfo">
                    <div id="username-container">
                        <h3>ka</h3>
                    </div>
                    <div id="user-desc-container">
                    <p></p>
                        <!-- <p>She got a body like an hourglass but I can give it to you all the time. She got a booty like a Cadillac but I can send you into overdrive.</p> -->
                    </div>
                </div>
            </div>
            

            <div id="usercontentnav">
                <ul>
                    <li><a class="navlink" href="" id="nav-current">My Photos</a></li>
                    <li><a class="navlink" href="#shared">Shared with me</a></li>
                </ul>
            </div>

            <div id="usercontent">
                <div class="tab_con" id="tab_con">
                    <a href="#" id="add-photo"><div class="tab_photo"><img class="thumbnail" src="add.png"/><div class="tab_title" id="tab-add-photo">Add photo</div></div></a>
                </div>
            </div>


            <div id="myModal" class="modal">

                    <span class="close" >&times;</span>
                    <div id="modal-container">
                        <div id="modal-container-2">
                            <div id="modal-photo-container">
                                <div id="modal-photo-center">
                                    <img class="modal-content" id="modalimg" />
                                </div>
                            </div>    
                            
                            <div id="modal-info-container">
                                <div id="caption"></div>
                                <div id="imguploader"></div>
                                <div id="imgalbum"></div>
                                <div id="imgcontainer">
                                    <div id="imgcontainer2">
                                    </div>
                                    <div class="imgtag" id="addtag"><i class="fa fa-plus" aria-hidden="true"></i><span id="add-tag"></span></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
            </div>

            <div id="modal-login" class="modal">

                <span class="close" >&times;</span>
                <div id="modal-login-container">
                    <div id="modal-login-container-2">
                        <div id="modal-login-content">
                            <h3>Log in</h3>
                            <form action="login" method="get" id="form-login">
                                <input type="text" name="username" placeholder="Username"><br>
                                <input type="password" name="password" placeholder="Password"><br>
                                <input type="submit" value="Log in" id="submit-login">
                            </form>
                        </div>
                    </div>
                </div>

            </div>

            <div id="modal-reg" class="modal">

                    <span class="close" >&times;</span>
                    <div id="modal-reg-container">
                        <div id="modal-reg-container-2">
                            <div id="modal-reg-content">
                                <h3>Register</h3>
                                <form action="register" method="post" id="form-reg">
                                    <input type="text" name="username" placeholder="Username"><br>
                                    <input type="password" name="password" placeholder="Password"><br>
                                    <input type="password" name="confirmpassword" placeholder="Confirm Password"><br>
                                    <textarea rows="3" name="description" placeholder="Short description about yourself (optional)"></textarea><br>
                                    <p class="form-error">Username should be at least 3 alphanumeric characters.</p>
                                    <p class="form-error">Password should be at least 8 alphanumeric characters.</p>
                                    <p class="form-error">Passwords do not match.</p>
                                    <input type="submit" value="Register" id="submit-register">
                                </form>
                            </div>
                        </div>
                    </div>
                    
                </div>


            <div id="modal-add-photo" class="modal">

                <span class="close" >&times;</span>
                <div id="modal-add-photo-container">
                    <div id="modal-add-photo-container-2">
                        <div id="modal-add-photo-content">
                            <h3>Upload photo</h3>
                                <form action="upload" method="post" id="form-upload">
                                    <input type="text" name="title" placeholder="Title"><br>
                                    <input type="text" name="tags" placeholder="Tags (comma-separated, ex. tag1, tag2)"><br>
                                    <textarea rows="3" name="description" placeholder="Short description about the photo"></textarea><br>
                                    <input type="file" name="file" id="file" accept=".jpg, .png, .tiff" class="pic-input" />
                                    <label for="file"><span><i class="fa fa-upload" aria-hidden="true"></i> Choose a file...</label></span><br>

                                    <ul>
                                        <li>
                                            <input type="radio" id="f-option" name="selector" class="pic-security" value="public">
                                            <label for="f-option">Public</label>
                                            <div class="check"></div>
                                        </li>

                                        <li>
                                            <input type="radio" id="s-option" name="selector" class="pic-security" value="private">
                                            <label for="s-option">Private</label>
                                            <div class="check"><div class="inside"></div></div>
                                        </li>
                                    </ul>
                                    <input type="text" name="allowed" placeholder="Allowed users (ex. user1, user2)" id="pic-allowed-user"><br>

            
                                    <input type="submit" value="Upload" id="submit-upload">
                                </form>
                        </div>
                    </div>
                </div>

            </div>

            <div id="footer">
                <div id="viewmore">
                    View More...
                </div>
            </div>

        </div>

    </body>
</html>