/**
 * 
 */

function addChapter(){
			var name = "2"; 
			var chaptername = document.getElementById("chaptername").value;
			if(chaptername !== "" && (chaptername.trim()) !== "") {
				//window.location.href="courseDetails.jsp?courseID=1";
				
				var chapterParent = document.getElementById("chapterParent");
				
				var group = document.createElement("div");
				group.setAttribute("id", "accordion");
				group.setAttribute("class", "panel-group");
				chapterParent.appendChild(group);
				
				var panel = document.createElement("div");
				panel.setAttribute("class", "panel panel-default");
				group.appendChild(panel);
				
				var heading = document.createElement("div");
				heading.setAttribute("class", "panel-heading");
				panel.appendChild(heading);

				var name = document.createElement("a");
				name.setAttribute("class", "panel-title");
				name.setAttribute("data-toggle", "collapse");
				name.setAttribute("data-parent", "accordion");
				name.setAttribute("href", "#" + name);	
				name.innerHTML = chaptername + "</br>";
				heading.appendChild(name);
				
				var add = document.createElement("a");
				add.setAttribute("id", "modal-2");
				add.setAttribute("class", "btn btn-primary");
				add.setAttribute("href", "#modal-container-2");
				add.setAttribute("data-toggle", "modal");
				add.innerHTML = "添加知识点";
				heading.appendChild(add);
				
				var points = document.createElement("div");
				points.setAttribute("id", name);
				points.setAttribute("class", "panel-collapse collapse in");
				
				panel.appendChild(points);
				
			} else {
				document.getElementById("alertC").style.display = "block";
			}
			
		}