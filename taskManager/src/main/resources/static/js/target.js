$(function(){
	
	var url = {
		target: "/target",
		document: "/document",
		api: "/api",
		getOne: "/get-one"
	};
	var formType = {
		create: "/create",
		update: "/update",
		delete: "/delete"
	};
	var $modal = {
		target : $("#target-modal"),
		targetDocument: $("#target-document-modal")
	};
	
	var modalType;
	var id;
	var name;
	var targetCategoryId;
	var targetCategoryName;
	var programCategoryId;
	var programCategoryName;
	var startDate;
	var endDate;
	var target;
	
	var targetId;
	var overview;
	var purpose;
	var func;
	var period;
	var documentId;
	var targetDocument;
	
	modalEvent();
	clickEvent();
	showReleasedTargetEvent();
	
	documentModalEvent();
	documentClickEvent();
	
	/**
	 *  Target Event
	 *
	 */
	 
	// show modal event
	function modalEvent(){
			$modal.target.on("show.bs.modal", function (event){
			modalReset();
			
			var $button = $(event.relatedTarget);
			id =  $button.data("id");
			modalType = $button[0].dataset.type;
			
			switch(modalType){
				case ("create"):
					$modal.target
						.find("#modal-title").text("Target create").end();
					break;
				case ("update"):
					getOne(id).done(function(res){
						target = res;
						$modal.target
							.find("#modal-title").text("Target update").end()
							.find("#name").val(target.name).end()
							.find("#targetCategoryId").val(target.targetCategoryId).end()
							.find("#programCategoryId").val(target.programCategoryId).end()
							.find("#startDate").val(target.startDate).end()
							.find("#endDate").val(target.endDate).end();
					}).fail(function(res){
						var message = "The error occured when get selected target.";
						$modal.target
								.find("#errorMessage").show().end()
								.find("#errorMessage").text(message).end();
					})
					break;
				case("delete"):
					targetCategoryName = $button.data("category-name");
					programCategoryName = $button.data("program-name");
					getOne(id).done(function(res){
						target = res;
						$modal.target
							.find("#modal-title").text("Target delete").end()
							.find("#name").hide().end()
							.find("#targetCategoryId").hide().end()
							.find("#programCategoryId").hide().end()
							.find("#startDate").hide().end()
							.find("#endDate").hide().end()
							.find("#deleteName").show().end()
							.find("#deleteName").text(target.name).end()
							.find("#deleteTargetCategory").show().end()
							.find("#deleteTargetCategory").text(targetCategoryName).end()
							.find("#deleteProgramCategory").show().end()
							.find("#deleteProgramCategory").text(programCategoryName).end()
							.find("#deleteStartDate").show().end()
							.find("#deleteStartDate").text(target.startDate).end()
							.find("#deleteEndDate").show().end()
							.find("#deleteEndDate").text(target.endDate).end();
					}).fail(function(res){
						var message =  "The error occured when get selected target.";
						$modal.target
								.find("#errorMessage").show().end()
								.find("#errorMessage").text(message).end();
					})
					break;
			}
		})
	}
	
	function clickEvent(){
		//click event to form
		$("#form-button").on("click", function(event) {
			setFormData();
			switch(modalType){
				case("create"):
					create().done(function(){
						window.location.reload();
					}).fail(function(res){
						var message = (res.responseJSON["message"]);
						showErrorMessage(message);
					})
					break;
				case("update"):
					update().done(function(){
						window.location.reload();
					}).fail(function(res){
						var message = (res.responseJSON["message"]);
						showErrorMessage(message);
					})
					break;
				case("delete"):
					deleteTarget().done(function(){
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
		$modal.target
			.find("#errorMessage").hide().end()
			.find("#name").show().end()
			.find("#name").val("").end()
			.find("#targetCategoryId").show().end()
			.find("#targetCategoryId").val(0).end()
			.find("#programCategoryId").show().end()
			.find("#programCategoryId").val(null).end()
			.find("#startDate").show().end()
			.find("#startDate").val("").end()
			.find("#endDate").show().end()
			.find("#endDate").val("").end()
			.find("#deleteName").hide().end()
			.find("#deleteTargetCategory").hide().end()
			.find("#deleteProgramCategory").hide().end()
			.find("#deleteStartDate").hide().end()
			.find("#deleteEndDate").hide().end();
	}
	
	//set form data
	function setFormData(){
		name = $modal.target.find("#name").val();
		targetCategoryId = $modal.target.find("#targetCategoryId").val();
		programCategoryId = $modal.target.find("#programCategoryId").val();
		startDate = $modal.target.find("#startDate").val();
		endDate = $modal.target.find("#endDate").val();
	}
	
	// show error message
	function showErrorMessage(message){
		var html = "";
		message.forEach( function( value ) {
		    html += "<span>" + value + "</span><br>"
		});
		$modal.target
			.find("#errorMessage").show().end()
			.find("#errorMessage").html(html).end();
	}
	
	
	//get target data
	function getOne(id){
		var def = new $.Deferred;
		$.ajax({
				url: url.target+ url.api + url.getOne ,
				type: 'GET',
				data: {id : id},
				dataType: 'json'
			}).done(function(res){
				def.resolve(res);
			}).fail(function(res){
				def.reject(res);
			})
		return def.promise();
	};
	
	//create function
	function create(){
		var def = new $.Deferred;
		$.ajax({
				url: url.target + url.api + formType.create,
				type: 'POST',
				data: {name : name, startDate: startDate, endDate: endDate, 
							programCategoryId: programCategoryId, targetCategoryId: targetCategoryId},
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
				url: url.target + url.api + formType.update,
				type: 'POST',
				data: {id: id, name : name, startDate: startDate, endDate: endDate, 
							programCategoryId: programCategoryId, targetCategoryId: targetCategoryId},
				dataType: 'json',
			}).done(function(res){
				def.resolve(res);
			}).fail(function(res){
				 def.reject(res);
			})
		return def.promise();
	}
	
	//delete function
	function deleteTarget(){
		var def = new $.Deferred;
		$.ajax({
				url: url.target + url.api + formType.delete,
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
	
	
	//Released target show event
	function showReleasedTargetEvent(){
		$('#showButton').click(function(){
			$('#endTargetTable').removeClass('fade');
			$('#showTableButton').addClass('fade');
		})
	}
	
	/**
	 *  Target Document Event
	 *
	 */
	 
	 // show modal event
	function documentModalEvent(){
			$modal.targetDocument.on("show.bs.modal", function (event){
			documentModalReset();
			
			var $button = $(event.relatedTarget);
			targetId = $button.data("target-id");
			documentId =  $button.data("id");
			modalType = $button.data("type");
			
			switch(modalType){
				case ("create"):
					$modal.targetDocument
						.find("#document-modal-title").text("Target document create").end();
					break;
				case ("update"):
					getOneDocument(documentId).done(function(res){
						targetDocument = res;
						$modal.targetDocument
							.find("#document-modal-title").text("Target document update").end()
							.find("#overview").val(targetDocument.overview).end()
							.find("#purpose").val(targetDocument.purpose).end()
							.find("#function").val(targetDocument.function).end()
							.find("#period").val(targetDOcument.period).end();
					}).fail(function(res){
						var message = "The error occured when get selected target document.";
						$modal.targetDocument
								.find("#document-errorMessage").show().end()
								.find("#document-errorMessage").text(message).end();
					})
					break;
			}
		})
	}
	
	function documentClickEvent(){
		//click event to form
		$("#document-form-button").on("click", function(event) {
			setFormDocumentData();
			switch(modalType){
				case("create"):
					documentCreate().done(function(){
						window.location.reload();
					}).fail(function(res){
						var message = (res.responseJSON["message"]);
						showDocumentErrorMessage(message);
					})
					break;
				case("update"):
					documentUpdate().done(function(){
						window.location.reload();
					}).fail(function(res){
						var message = (res.responseJSON["message"]);
						showDocumentErrorMessage(message);
					})
					break;
			}
		})
	}
	
	//modal data reset function
	function documentModalReset(){
		$modal.targetDocument
			.find("#document-errorMessage").hide().end()
			.find("#overview").val("").end()
			.find("#purpose").val("").end()
			.find("#function").val("").end()
			.find("#period").val("").end();
	}
	
	//set form data
	function setFormDocumentData(){
		overview = $modal.targetDocument.find("#overview").val();
		purpose = $modal.targetDocument.find("#purpose").val();
		func = $modal.targetDocument.find("#function").val();
		period = $modal.targetDocument.find("#period").val();
	}
	
	// show erro message
	function showDocumentErrorMessage(message){
		var html = "";
		message.forEach( function( value ) {
		     html += "<span>" + value + "</span><br>"
		});
		$modal.targetDocument
			.find("#document-errorMessage").show().end()
			.find("#document-errorMessage").html(html).end();
	}
	
	
	//get target data
	function getOneDocument(documentId){
		var def = new $.Deferred;
		$.ajax({
				url: url.target + url.api + url.document +  url.getOne ,
				type: 'GET',
				data: {id : documentId},
				dataType: 'json'
			}).done(function(res){
				def.resolve(res);
			}).fail(function(res){
				def.reject(res);
			})
		return def.promise();
	};
	
	//create function
	function documentCreate(){
		var def = new $.Deferred;
		$.ajax({
				url: url.target + url.api + url.document + formType.create,
				type: 'POST',
				data: {overview: overview, purpose: purpose, function: func, period: period, targetId: targetId},
				dataType: 'json',
			}).done(function(res){
				def.resolve(res);
			}).fail(function(res){
				 def.reject(res);
			})
		return def.promise();
	}
	
	//update function
	function documentUpdate(){
		var def = new $.Deferred;
		$.ajax({
				url: url.target + url.api + url.document + formType.update,
				type: 'POST',
				data: {id: documentId, overview: overview, purpose: purpose, function: func, period: period},
				dataType: 'json',
			}).done(function(res){
				def.resolve(res);
			}).fail(function(res){
				 def.reject(res);
			})
		return def.promise();
	}
	
})