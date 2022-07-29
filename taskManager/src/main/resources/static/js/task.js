$(function(){
	
	var url = {
		task: "/task",
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
		task : $("#task-modal"),
		taskDocument : $("#task-document-modal")
	};
	
	var modalType;
	var id;
	var overview;
	var systemId;
	var systemName;
	var startDate;
	var endDate;
	var report;
	var task;
	var taskId;
	var purpose;
	var func;
	var item;
	var period;
	var documentId;
	var taskDocument;
	
	modalEvent();
	clickEvent();
	showComplateTaskEvent();
	documentModalEvent();
	documentClickEvent();
	
	/**
	 * Task Evennt
	 *
	 */
	
	// show modal event
	function modalEvent(){
			$modal.task.on("show.bs.modal", function (event){
			modalReset();
			
			var $button = $(event.relatedTarget);
			id =  $button.data("id");
			modalType = $button[0].dataset.type;
			
			switch(modalType){
				case ("create"):
					$modal.task
						.find("#modal-title").text("Task create").end();
					break;
				case ("update"):
					getOne(id).done(function(res){
						task = res;
						$modal.task
							.find("#modal-title").text("Task update").end()
							.find("#overview").val(task.overview).end()
							.find("#systemId").val(task.systemId).end()
							.find("#startDate").val(task.startDate).end()
							.find("#endDate").val(task.endDate).end()
							.find("#report").val(task.report).end();
					}).fail(function(res){
						var message = "The error occured when get selected task.";
						$modal.task
								.find("#errorMessage").show().end()
								.find("#errorMessage").text(message).end();
					})
					break;
				case("delete"):
					systemName = $button.data("system-name");
					getOne(id).done(function(res){
						task = res;
						$modal.task
							.find("#modal-title").text("Task delete").end()
							.find("#overview").hide().end()
							.find("#systemId").hide().end()
							.find("#startDate").hide().end()
							.find("#endDate").hide().end()
							.find("#report").hide().end()
							.find("#deleteOverview").show().end()
							.find("#deleteOverview").text(task.overview).end()
							.find("#deleteSystem").show().end()
							.find("#deleteSystem").text(systemName).end()
							.find("#deleteStartDate").show().end()
							.find("#deleteStartDate").text(task.startDate).end()
							.find("#deleteEndDate").show().end()
							.find("#deleteEndDate").text(task.endDate).end()
							.find("#deleteReport").show().end()
							.find("#deleteReport").text(task.report).end();
					}).fail(function(res){
						var message =  "The error occured when get selected task.";
						$modal.task
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
					deleteTask().done(function(){
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
		$modal.task
			.find("#errorMessage").hide().end()
			.find("#overview").show().end()
			.find("#overview").val("").end()
			.find("#systemId").show().end()
			.find("#systemId").val(0).end()
			.find("#startDate").show().end()
			.find("#startDate").val("").end()
			.find("#endDate").show().end()
			.find("#endDate").val("").end()
			.find("#report").show().end()
			.find("#report").val("").end()
			.find("#deleteOverview").hide().end()
			.find("#deleteSystem").hide().end()
			.find("#deleteStartDate").hide().end()
			.find("#deleteEndDate").hide().end()
			.find("#deleteReport").hide().end();
	}
	
	//set form data
	function setFormData(){
		overview = $modal.task.find("#overview").val();
		systemId = $modal.task.find("#systemId").val();
		startDate = $modal.task.find("#startDate").val();
		endDate = $modal.task.find("#endDate").val();
		report = $modal.task.find("#report").val();
	}
	
	// show erro message
	function showErrorMessage(message){
		var html = "";
		message.forEach( function( value ) {
		     html += "<span>" + value + "</span><br>"
		});
		$modal.task
			.find("#errorMessage").show().end()
			.find("#errorMessage").html(html).end();
	}
	
	
	
	//get task data
	function getOne(id){
		var def = new $.Deferred;
		$.ajax({
				url: url.task+ url.api + url.getOne ,
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
				url: url.task + url.api + formType.create,
				type: 'POST',
				data: {overview : overview, startDate: startDate, endDate: endDate, report: report, systemId: systemId},
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
				url: url.task + url.api + formType.update,
				type: 'POST',
				data: {id: id, overview : overview, startDate: startDate, endDate: endDate, report: report, systemId: systemId},
				dataType: 'json',
			}).done(function(res){
				def.resolve(res);
			}).fail(function(res){
				 def.reject(res);
			})
		return def.promise();
	}
	
	//delete function
	function deleteTask(){
		var def = new $.Deferred;
		$.ajax({
				url: url.task + url.api + formType.delete,
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
	
	
	//Complated task show event
	function showComplateTaskEvent(){
		$('#showButton').click(function(){
			$('#endTaskTable').removeClass('fade');
			$('#showTableButton').addClass('fade');
		})
	}
	
	/**
	 *  Task Document Event
	 *
	 */
	 
	 // show modal event
	function documentModalEvent(){
			$modal.taskDocument.on("show.bs.modal", function (event){
			documentModalReset();
			
			var $button = $(event.relatedTarget);
			taskId = $button.data("task-id");
			documentId =  $button.data("id");
			modalType = $button.data("type");
			
			switch(modalType){
				case ("create"):
					$modal.taskDocument
						.find("#document-modal-title").text("Task document create").end();
					break;
				case ("update"):
					getOneDocument(documentId).done(function(res){
						taskDocument = res;
						$modal.taskDocument
							.find("#document-modal-title").text("Task document update").end()
							.find("#purpose").val(taskDocument.purpose).end()
							.find("#function").val(taskDocument.function).end()
							.find("#item").val(taskDocument.item).end()
							.find("#period").val(taskDOcument.period).end();
					}).fail(function(res){
						var message = "The error occured when get selected task document.";
						$modal.taskDocument
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
		$modal.taskDocument
			.find("#document-errorMessage").hide().end()
			.find("#purpose").val("").end()
			.find("#function").val("").end()
			.find("#item").val("").end()
			.find("#period").val("").end();
	}
	
	//set form data
	function setFormDocumentData(){
		purpose = $modal.taskDocument.find("#purpose").val();
		func = $modal.taskDocument.find("#function").val();
		item = $modal.taskDocument.find("#item").val();
		period = $modal.taskDocument.find("#period").val();
	}
	
	// show erro message
	function showDocumentErrorMessage(message){
		var html = "";
		message.forEach( function( value ) {
		     html += "<span>" + value + "</span><br>"
		});
		$modal.taskDocument
			.find("#document-errorMessage").show().end()
			.find("#document-errorMessage").html(html).end();
	}
	
	//get system data
	function getOneDocument(documentId){
		var def = new $.Deferred;
		$.ajax({
				url: url.task+ url.api + url.document +  url.getOne ,
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
				url: url.task + url.api + url.document + formType.create,
				type: 'POST',
				data: {purpose: purpose, function: func, item: item, period: period, taskId: taskId},
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
				url: url.task + url.api + url.document + formType.update,
				type: 'POST',
				data: {id: documentId, purpose: purpose, function: func, item: item, period: period},
				dataType: 'json',
			}).done(function(res){
				def.resolve(res);
			}).fail(function(res){
				 def.reject(res);
			})
		return def.promise();
	}
		
})