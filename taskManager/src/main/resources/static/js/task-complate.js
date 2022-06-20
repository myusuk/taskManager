//データ取得
	function getone(id){
		$.ajax({
				url: 'api'  + '/get-one/' + id,
				type: 'GET',
				data: {id : id},
				dataType: 'json'
			}).done(function(res){
				setData(res);
			}).fail(function(){
			})
	};
	
	//モーダルにデータをセット
	function setData(data){
		$('#taskId').val(data.id);
		$('#systemId').val(data.systemId);
		$('#featureNumber').val(data.featureNumber);
		$('#featureNumberText').text(data.featureNumber);
		$('#overview').val(data.overview);
		$('#overviewText').text(data.overview);
		$('#startDate').val(data.startDate);
		$('#startDateText').text(data.startDate);
	}
	
	$('#task-modal').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget);
		var id = button.data('id');
		getone(id);
		document.getElementById("task-form").setAttribute("action", "/task/edit");
		$('#modal-title').text('タスク編集');
	})
	
		/***** document-modal  *****/
	
		//入力値にエラーがあったらモーダルを開く
	$(document).ready(function(){
		var error = $('#docerror').text();
		if(error != ""){
			$('#task-document-modal').modal('show');
		}
	})
	
		function getonedocument(id){
		$.ajax({
				url:  'api'  + '/document/get-one/' + id,
				type: 'GET',
				data: {id : id},
				dataType: 'json'
			}).done(function(res){
				setDocumentData(res);
			}).fail(function(){
			})
	};
	
		//モーダルにデータをセット
	function setDocumentData(data){
		$('#purpose').val(data.purpose);
		$('#function').val(data.function);
		$('#item').val(data.item);
		$('#period').val(data.period);
	}
	
	$('#task-document-modal').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget);
		if(button[0] != undefined){
			var id = button.data('id');
			if(id != null){
				getonedocument(id);
			}
			$('#docErrorMessage').text("");
		}else{
			var id = $('#sendId').text();
			if(id != null){
				getonedocument(id);
			}
		}
		
	})