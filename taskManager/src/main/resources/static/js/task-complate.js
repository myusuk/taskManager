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
	
	//モーダル内容をリセット
	function modalReset(){
	}
	
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
	
	//モーダルを入力不可にする
	
	
	$('#task-modal').on('show.bs.modal', function (event) {
		
		var button = $(event.relatedTarget);
		var type = button.context.dataset.type;
		var id = button.data('id');
		
		modalReset();
		
		switch(type){
			case 'edit':
			getone(id);
			document.getElementById("task-form").setAttribute("action", "/task/edit");
			$('#modal-title').text('タスク編集');
			break;
			case 'delete':
			getone(id);
			document.getElementById("task-form").setAttribute("action", "/task/delete");
			$('#modal-title').text('タスク削除');
			break;
		}
	})
	