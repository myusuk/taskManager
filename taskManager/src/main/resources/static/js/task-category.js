$(function(){
	
	var url = {
		taskCategory: "/task-category",
		api: "/api"
	};
	var formType = {
		create: "/create",
		update: "/update",
		delete: "/delete"
	};
	var $modal = {
		taskCategory : $("#task-category-modal")
	};
	
	var modalType;
	var id;
	var name;
	
	modalEvent();
	clickEvent();
	
	// show modal event
	function modalEvent(){
			$modal.taskCategory.on("show.bs.modal", function (event){
			modalReset();
			
			var $button = $(event.relatedTarget);
			id =  $button.data("id");
			name =  $button.data("name");
			modalType = $button[0].dataset.type;
			
			switch(modalType){
				case ("create"):
					$modal.taskCategory
						.find("#modal-title").text("Task category create").end();
					break;
				case ("update"):
					$modal.taskCategory
						.find("#modal-title").text("Task category update").end()
						.find("#name").val(name).end();
					break;
				case("delete"):
					$modal.taskCategory
						.find("#modal-title").text("Task category delete").end()
						.find("#name").hide().end()
						.find("#deleteName").show().end()
						.find("#deleteName").text(name).end();
					break;
			}
		})
	}
	
	function clickEvent(){
		//click event to form
		$("#form-button").on("click", function(event) {
			switch(modalType){
				case("create"):
					name = $("#name").val();
					create().done(function(){
						window.location.reload();
					}).fail(function(res){
						var message = (res.responseJSON["message"]);
						showErrorMessage(message);
					})
					break;
				case("update"):
					name = $("#name").val();
					update().done(function(){
						window.location.reload();
					}).fail(function(res){
						var message = (res.responseJSON["message"]);
						showErrorMessage(message);
					})
					break;
				case("delete"):
					deleteTaskCategory().done(function(){
							window.location.reload();
						}).fail(function(res){
							var message = (res.responseJSON["message"]);
							showErrorMessage(message);
						})
					break;
			}
		})
	}
	
	
	
	//modal data reset function
	function modalReset(){
		$modal.taskCategory
			.find("#errorMessage").hide().end()
			.find("#name").show().end()
			.find("#name").val("").end()
			.find("#deleteName").hide().end();
	}
	
	// show error message
	function showErrorMessage(message){
		var html = "";
		message.forEach( function( value ) {
		     html += "<span>" + value + "</span><br>"
		});
		$modal.taskCategory
			.find("#errorMessage").show().end()
			.find("#errorMessage").html(html).end();
	}
	
	//create function
	function create(){
		var def = new $.Deferred;
		$.ajax({
				url: url.taskCategory + url.api + formType.create,
				type: 'POST',
				data: {name : name},
				dataType: 'json',
			}).done(function(res){
				def.resolve(res);
			}).fail(function(res){
				 def.reject(res);
			})
		return def.promise();
	}
	
	//update function
	function update(){
		var def = new $.Deferred;
		$.ajax({
				url: url.taskCategory + url.api + formType.update,
				type: 'POST',
				data: {id: id, name : name},
				dataType: 'json',
			}).done(function(res){
				def.resolve(res);
			}).fail(function(res){
				 def.reject(res);
			})
		return def.promise();
	}
	
	//delete function
	function deleteTaskCategory(){
		var def = new $.Deferred;
		$.ajax({
				url: url.taskCategory + url.api + formType.delete,
				type: 'POST',
				data: {id: id},
				dataType: 'json',
			}).done(function(res){
				def.resolve(res);
			}).fail(function(res){
				 def.reject(res);
			})
		return def.promise();
	}
		
		
})

