$(function(){
	
	var url = {
		targetCategory: "/target-category",
		api: "/api"
	};
	var formType = {
		create: "/create",
		update: "/update",
		delete: "/delete"
	};
	var $modal = {
		targetCategory : $("#target-category-modal")
	};
	
	var modalType;
	var id;
	var name;
	
	modalEvent();
	clickEvent();
	
	// show modal event
	function modalEvent(){
			$modal.targetCategory.on("show.bs.modal", function (event){
			modalReset();
			
			var $button = $(event.relatedTarget);
			id =  $button.data("id");
			name =  $button.data("name");
			modalType = $button[0].dataset.type;
			
			switch(modalType){
				case ("create"):
					$modal.targetCategory
						.find("#modal-title").text("Target category create").end();
					break;
				case ("update"):
					$modal.targetCategory
						.find("#modal-title").text("Target category update").end()
						.find("#name").val(name).end();
					break;
				case("delete"):
					$modal.targetCategory
						.find("#modal-title").text("Target category delete").end()
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
					deleteTargetCategory().done(function(){
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
		$modal.targetCategory
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
		$modal.targetCategory
			.find("#errorMessage").show().end()
			.find("#errorMessage").html(html).end();
	}
	
	//create function
	function create(){
		var def = new $.Deferred;
		$.ajax({
				url: url.targetCategory + url.api + formType.create,
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
				url: url.targetCategory + url.api + formType.update,
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
	function deleteTargetCategory(){
		var def = new $.Deferred;
		$.ajax({
				url: url.targetCategory + url.api + formType.delete,
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

