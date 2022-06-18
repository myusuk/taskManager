//データ取得
	function getone(id){
		$.ajax({
				url: 'task' + '/api'  + '/get-one/' + id,
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
		$('#errorMessage').text("");
		$('#taskId').val("");
		$('#systemId').val(0);
		$('#featureNumber').val("");
		$('#overview').val("");
		$('#startDate').val("");
		document.getElementById("systemId").disabled = false;
		document.getElementById("featureNumber").disabled = false;
		document.getElementById("overview").disabled = false;
		document.getElementById("startDate").disabled = false;
	}
	
	//モーダルにデータをセット
	function setData(data){
		$('#taskId').val(data.id);
		$('#systemId').val(data.systemId);
		$('#featureNumber').val(data.featureNumber);
		$('#overview').val(data.overview);
		$('#startDate').val(data.startDate);
	}
	
	//モーダルを入力不可にする
	function disable(){
		document.getElementById("systemId").disabled = true;
		document.getElementById("featureNumber").disabled = true;
		document.getElementById("overview").disabled = true;
		document.getElementById("startDate").disabled = true;
	}
	
	//入力不可を解除
	function able(){
		document.getElementById("systemId").disabled = false;
		document.getElementById("featureNumber").disabled = false;
		document.getElementById("overview").disabled = false;
		document.getElementById("startDate").disabled = false;
	}
	
	$('#create-button').click(function(){
		able();
	})
	
	//入力値にエラーがあったらモーダルを開く
	$(document).ready(function(){
		var error = $('#error').text();
		if(error != ""){
			$('#task-modal').modal('show');
		}
	})
	
	//モーダル表示時処理
	$('#task-modal').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget);
		if(button[0] != undefined){
			var type = button[0].dataset.type;
			var id = button.data('id');
			modalReset();
		}else{
			var type = $('#formType').text();
			var id = $('#sendId').text();
		}
		switch(type){
			case 'register':
			document.getElementById("task-form").setAttribute("action", "/task/create");
			$('#modal-title').text('タスク登録');
			break;
			case 'edit':
			getone(id);
			document.getElementById("task-form").setAttribute("action", "/task/edit");
			$('#modal-title').text('タスク編集');
			break;
			case 'delete':
			getone(id);
			document.getElementById("task-form").setAttribute("action", "/task/delete");
			disable();
			$('#modal-title').text('タスク削除');
			break;
		
		}
	})
	
	//完了タスク表示
	$('#showButton').click(function(){
		$('#endTaskTable').removeClass('fade');
		$('#showTableButton').addClass('fade');
	})
	