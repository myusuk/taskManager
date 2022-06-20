//データ取得
	function getone(id){
		$.ajax({
				url:  'api'  + '/get-one/' + id,
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
		$('#systemId').val(data.id);
		$('#systemName').val(data.systemName);
		$('#languageId').val(data.languageId);
		$('#startDate').val(data.startDate);
	}
	
	$('#system-modal').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget);
		var id = button.data('id');
		getone(id);
		document.getElementById("system-form").setAttribute("action", "/system/edit");
		$('#modal-title').text('システム編集');
	})
	
	/***** document-modal  *****/
	
		//入力値にエラーがあったらモーダルを開く
	$(document).ready(function(){
		var error = $('#docerror').text();
		if(error != ""){
			$('#system-document-modal').modal('show');
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
		$('#overview').val(data.overview);
		$('#purpose').val(data.purpose);
		$('#function').val(data.function);
		$('#period').val(data.period);
	}
	
	$('#system-document-modal').on('show.bs.modal', function (event) {
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
	