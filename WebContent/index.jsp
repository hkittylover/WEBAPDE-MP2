<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Home | Oink</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="jquery-3.2.1.min.js"></script>
        <link rel = "stylesheet" href = "stylesheet.css">
       
        <script>
            var root = 'https://jsonplaceholder.typicode.com';
            var allphotos = [];
            var photos_cnt = 0;
            var numViewPhoto = 5;

            function Photo(username, title, description, filepath, privacy, date, tags, allowedUsers) {
            	this.username = username;
            	this.title = title;
            	this.description = description;
            	this.filepath = filepath;
            	this.privacy = privacy;
            	this.date = new Date(date);
            	this.tags = tags;
            	this.allowedUsers = allowedUsers;
            }
            
            function showPhoto(photoObj) {
                var a_photo = document.createElement("a");

                var new_div_tab_photo = document.createElement("div");
                new_div_tab_photo.className += "tab_photo";

                var new_tab_title = document.createElement("div");
                new_tab_title.className += "tab_title";
                new_tab_title.textContent = photoObj.title;
                //new_div_tab_photo.className += "tab_photo";

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

                // add link to div
                a_photo.appendChild(new_div_tab_photo);
                
                $("#tab_con").append(a_photo);
                $(new_div_tab_photo).show(500);
            }

            function getPhotos(){
            	/*
                $.ajax({
                    url: root + '/photos/'
                    , method: 'GET'
                }).then(function (photos) {
                    for (i = photos.length - photos_cnt - 1; i > photos.length - photos_cnt - numViewPhoto - 1 && i >= 0; i--) {
                        showPhoto(photos[i]);
                        console.log(photos[i].id);
                    }
                    photos_cnt = photos_cnt + numViewPhoto;
                    if (photos_cnt >= photos.length) {
                        $("#viewmore").hide();
                    }
                });
            	*/
            	pList = ${pList};/*
            	var i = 0;
				for (i = 0; i < ${pListSize}; i++) {
	        		var p = ${pList[i]};
					var photo = new Photo(${p.username}, ${p.title}, ${p.description}, ${p.filepath}, ${p.privacy}, ${p.date}, ${p.tags}, ${p.allowedUsers});
	        		pList.push(photo);
				}*/
				//<c:forEach var="p" items="${pList}" >
				//  var photo = ${p};
				//  pList.push(photo);
				//</c:forEach>
				var i;
				var limit = photos_cnt + numViewPhoto;
            	for(i = photos_cnt; i < pList.length && i < limit; i++) {
            		showPhoto(pList[i]);
            		photos_cnt++;
            	}
            	
                if (photos_cnt >= pList.length) {
                    $("#viewmore").hide();
                }
            }
            
            function findPhoto(pList, id) {
            	for(i = 0; i < pList.length; i++) {
            		if(pList[i].photoId == id) {
            			console.log(pList[i]);
            			return pList[i];
            		}
            		console.log("Hello");
            	}
            	return null;
            }
            
            function showImgModal() {
            	pList = ${pList};
            	var id = parseInt(window.location.hash.slice(1));
            	p = findPhoto(pList, id);
            	console.log(parseInt(window.location.hash.slice(1)) - 1)
                console.log("show the modal thing");
                //console.log($(this).data());

                //get value of id from the child div (which is id div)
                var modal_thing = document.getElementById("myModal");
                var modal_img = document.getElementById("modalimg");
                var modal_caption = document.getElementById("caption");
                var modal_uploader = document.getElementById("imguploader");
                var modal_album = document.getElementById("imgalbum");

                $(modal_album).empty();
                $(modal_img).empty();
                $(modal_uploader).empty();

                var a_user = document.createElement("a");
                var a_album = document.createElement("a");
				
                document.title = p.title + " | Photos | Oink";
                modal_caption.innerHTML = p.title;
                modal_img.src = p.filepath;
                
                a_user.href = "userpage.html?id=" + p.username + "#posts";
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
                /*
                // get the photo title
                $.ajax({
                    url: root + '/photos/' + window.location.hash.slice(1)
                    , method: 'GET'
                }).then(function (photos) {
                    // get the photo title
                   
                    //modal_img.src = photos.url;

                    // get the photo album name
                    $.ajax({
                        url: root + '/albums/' + photos.albumId
                        , method: 'GET'
                    }).then(function (album) {
                        //a_album.href = "album_single.html?id=" + photos.albumId;
                        //a_album.textContent = "View more: " + album.title;
                        //modal_album.appendChild(a_album);
                            
                        // get the photo uploader username
                        $.ajax({
                            url: root + '/users/' + album.userId
                            , method: 'GET'
                        }).then(function (user) {
                            a_user.href = "userpage.html?id=" + user.id + "#posts";
                            a_user.textContent = user.username;
                            modal_uploader.appendChild(a_user);

                            modal_thing.style.display = "table";
                        });
                    }); 
                }); 
*/
                
            }

            $(document).ready(function () {
                getPhotos();

                // if address has non-empty hash, show equivalent modal
                // if(window.location.hash.slice(1) != "") {
                //     showImgModal();
                // }

                // gets more photos when View More is clicked
                $("#viewmore").click(function (e) {
                    e.preventDefault();
                    getPhotos();
                });

                // on hash change, show photo
                $(window).on('hashchange',function(e){
                	//e.preventDefault();
                    if(window.location.hash.slice(1) != "")
                        showImgModal();
                });

                // close modal on x button
                $(document).on("click", ".close", function(e){
                	//e.preventDefault();
                    console.log("close modal");
                    window.location.hash = "";
                    document.title = "Oink";
                    
                    var modal = document.getElementById("myModal");
                    var modallog = document.getElementById("modal-login");
                    
                    modal.style.display='none';
                    modallog.style.display = 'none';
                });
                
                // close modal after any click outside modal
                $(window).on("click", function(e) {
                	//e.preventDefault();
                    window.location.hash = "";
                    document.title = "Oink";

                    var modal = document.getElementById("myModal");
                    var modallog = document.getElementById("modal-login");
                    var modalc = document.getElementById("modal-container");
                    var modalclog = document.getElementById("modal-login-container");
                    if(event.target == modal || event.target == modalc || event.target == modallog || event.target == modalclog) {
                        modal.style.display = 'none';
                        modallog.style.display = 'none';
                    }

                    console.log("onclick: " + event.target);
                });

                $('a.hlink').click(function(e){
                	//e.preventDefault();
                    console.log(event.target.text)
                    event.preventDefault(); 
                    var hlinklist = document.getElementsByClassName("hlink");
                    for(i = 0; i < hlinklist.length; i++) {
                        if(event.target.text == hlinklist[i].text && hlinklist[i].text == "Login") {
                            var modallog = document.getElementById("modal-login");
                            modallog.style.display = 'table';
                        }
                    }

                    return false; 
                });
            });

        </script>
        <title>Oink</title>
	</head>
	<body>
	<div id="header">
            <div id="hleft">
                <a id="hlogo" href="index.html">OINK</a>
            </div>
            <div id="hright">
                <form action="" method="post" class="index-search-form" name="">
                    <input name="search" type="text" placeholder="What are you looking for?">
                    <button name="submit" class="" type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>
                </form>
                <!-- <a class="hlink" href="index.html">Search</a> -->
                <!-- <a class="hlink" href="photos.html">Photos</a> -->
                <a class="hlink" href="index.html">Login</a> 
                <a class="hlink" href="index.html">Register</a> 
            </div>
        </div>

        
        <div id="bodypanel">

            <div class="tab_con" id="tab_con">

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
                                <!-- 
                                    <div class="imgtag">#pretty</div>
                                    <div class="imgtag">#awesome</div>
                                    <div class="imgtag">#blessed</div>
                                    <div class="imgtag">#dogs</div>
                                    <div class="imgtag">#dogs</div>
                                    <div class="imgtag">#dogs</div>
                                    <div class="imgtag">#dogs</div>
                                    <div class="imgtag">#dogs</div>
                                    <div class="imgtag">#dogs</div>
                                    <div class="imgtag">#dogs</div>-->
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
                                <form action="">
                                    <input type="text" name="username" placeholder="Username"><br>
                                    <input type="password" name="lastname" placeholder="Password"><br>
                                    <input type="submit" value="Log in">
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
                                <h3>Log in</h3>
                                <form action="">
                                    <input type="text" name="username" placeholder="Username"><br>
                                    <input type="text" name="lastname" placeholder="Password"><br>
                                    <input type="submit" value="Log in">
                                </form>
                            </div>
                        </div>
                    </div>
                    
                </div>

                
            </div>
            
        </div>
        <div id="footer">
            <div id="viewmore">
                View More...
            </div>
        </div>

    </body>

</html>


<div style="display:flex;justify-content:center;align-items:center;width:400px;height:370px;">
  <div></div>
</div>