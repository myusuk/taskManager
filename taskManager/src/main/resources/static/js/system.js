$(function(){
	
	var url = {
		system: "/system",
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
		system : $("#system-modal"),
		systemDocument: $("#system-document-modal")
	};
	
	var modalType;
	var id;
	var name;
	var languageId;
	var languageName;
	var startDate;
	var endDate;
	var system;
	var systemId;
	var overview;
	var purpose;
	var func;
	var period;
	var documentId;
	var systemDocument;
	
	modalEvent();
	clickEvent();
	showReleasedSystemEvent();
	documentModalEvent();
	documentClickEvent();
	
	/**
	 *  System Event
	 *
	 */
	 
	// show modal event
	function modalEvent(){
			$modal.system.on("show.bs.modal", function (event){
			modalReset();
			
			var $button = $(event.relatedTarget);
			id =  $button.data("id");
			modalType = $button[0].dataset.type;
			
			switch(modalType){
				case ("create"):
					$modal.system
						.find("#modal-title").text("System create").end();
					break;
				case ("update"):
					getOne(id).done(function(res){
						system = res;
						$modal.system
							.find("#modal-title").text("System update").end()
							.find("#name").val(system.name).end()
							.find("#languageId").val(system.languageId).end()
							.find("#startDate").val(system.startDate).end()
							.find("#endDate").val(system.endDate).end();
					}).fail(function(res){
						var message = "The error occured when get selected system.";
						$modal.system
								.find("#errorMessage").show().end()
								.find("#errorMessage").text(message).end();
					})
					break;
				case("delete"):
					languageName = $button.data("language-name");
					getOne(id).done(function(res){
						system = res;
						$modal.system
							.find("#modal-title").text("System delete").end()
							.find("#name").hide().end()
							.find("#languageId").hide().end()
							.find("#startDate").hide().end()
							.find("#endDate").hide().end()
							.find("#deleteName").show().end()
							.find("#deleteName").text(system.name).end()
							.find("#deleteLanguage").show().end()
							.find("#deleteLanguage").text(languageName).end()
							.find("#deleteStartDate").show().end()
							.find("#deleteStartDate").text(system.startDate).end()
							.find("#deleteEndDate").show().end()
							.find("#deleteEndDate").text(system.endDate).end();
					}).fail(function(res){
						var message =  "The error occured when get selected system.";
						$modal.system
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
					deleteSystem().done(function(){
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
		$modal.system
			.find("#errorMessage").hide().end()
			.find("#name").show().end()
			.find("#name").val("").end()
			.find("#languageId").show().end()
			.find("#languageId").val(0).end()
			.find("#startDate").show().end()
			.find("#startDate").val("").end()
			.find("#endDate").show().end()
			.find("#endDate").val("").end()
			.find("#deleteName").hide().end()
			.find("#deleteLanguage").hide().end()
			.find("#deleteStartDate").hide().end()
			.find("#deleteEndDate").hide().end();
	}
	
	//set form data
	function setFormData(){
		name = $modal.system.find("#name").val();
		languageId = $modal.system.find("#languageId").val();
		startDate = $modal.system.find("#startDate").val();
		endDate = $modal.system.find("#endDate").val();
	}
	
	// show erro message
	function showErrorMessage(message){
		var html = "";
		message.forEach( function( value ) {
		    html += "<span>" + value + "</span><br>"
		});
		$modal.system
			.find("#errorMessage").show().end()
			.find("#errorMessage").html(html).end();
	}
	
	
	//get system data
	function getOne(id){
		var def = new $.Deferred;
		$.ajax({
				url: url.system+ url.api + url.getOne ,
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
				url: url.system + url.api + formType.create,
				type: 'POST',
				data: {name : name, startDate: startDate, endDate: endDate, languageId: languageId},
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
				url: url.system + url.api + formType.update,
				type: 'POST',
				data: {id: id, name : name, startDate: startDate, endDate: endDate, languageId: languageId},
				dataType: 'json',
			}).done(function(res){
				def.resolve(res);
			}).fail(function(res){
				 def.reject(res);
			})
		return def.promise();
	}
	
	//delete function
	function deleteSystem(){
		var def = new $.Deferred;
		$.ajax({
				url: url.system + url.api + formType.delete,
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
	
	
	//Released system show event
	function showReleasedSystemEvent(){
		$('#showButton').click(function(){
			$('#endSystemTable').removeClass('fade');
			$('#showTableButton').addClass('fade');
		})
	}
	
	/**
	 *  System Document Event
	 *
	 */
	 
	 // show modal event
	function documentModalEvent(){
			$modal.systemDocument.on("show.bs.modal", function (event){
			documentModalReset();
			
			var $button = $(event.relatedTarget);
			systemId = $button.data("system-id");
			documentId =  $button.data("id");
			modalType = $button.data("type");
			
			switch(modalType){
				case ("create"):
					$modal.systemDocument
						.find("#document-modal-title").text("System document create").end();
					break;
				case ("update"):
					getOneDocument(documentId).done(function(res){
						systemDocument = res;
						$modal.systemDocument
							.find("#document-modal-title").text("System document update").end()
							.find("#overview").val(systemDocument.overview).end()
							.find("#purpose").val(systemDocument.purpose).end()
							.find("#function").val(systemDocument.function).end()
							.find("#period").val(systemDOcument.period).end();
					}).fail(function(res){
						var message = "The error occured when get selected system document.";
						$modal.systemDocument
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
		$modal.systemDocument
			.find("#document-errorMessage").hide().end()
			.find("#overview").val("").end()
			.find("#purpose").val("").end()
			.find("#function").val("").end()
			.find("#period").val("").end();
	}
	
	//set form data
	function setFormDocumentData(){
		overview = $modal.systemDocument.find("#overview").val();
		purpose = $modal.systemDocument.find("#purpose").val();
		func = $modal.systemDocument.find("#function").val();
		period = $modal.systemDocument.find("#period").val();
	}
	
	// show erro message
	function showDocumentErrorMessage(message){
		var html = "";
		message.forEach( function( value ) {
		     html += "<span>" + value + "</span><br>"
		});
		$modal.systemDocument
			.find("#document-errorMessage").show().end()
			.find("#document-errorMessage").html(html).end();
	}
	
	
	//get system data
	function getOneDocument(documentId){
		var def = new $.Deferred;
		$.ajax({
				url: url.system+ url.api + url.document +  url.getOne ,
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
				url: url.system + url.api + url.document + formType.create,
				type: 'POST',
				data: {overview: overview, purpose: purpose, function: func, period: period, systemId: systemId},
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
				url: url.system + url.api + url.document + formType.update,
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