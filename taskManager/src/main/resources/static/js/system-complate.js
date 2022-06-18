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
	