//データ取得
	function getone(id){
		$.ajax({
				url: 'system' + '/api'  + '/get-one/' + id,
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
		$('#systemId').val("");
		$('#systemName').val("");
		$('#languageId').val(0);
		$('#startDate').val("");
		document.getElementById("systemName").disabled = false;
		document.getElementById("languageId").disabled = false;
		document.getElementById("startDate").disabled = false;
	}
	
	//モーダルにデータをセット
	function setData(data){
		$('#systemId').val(data.id);
		$('#systemName').val(data.systemName);
		$('#languageId').val(data.languageId);
		$('#startDate').val(data.startDate);
	}
	
	//モーダルを入力不可にする
	function disable(){
		document.getElementById("systemName").disabled = true;
		document.getElementById("languageId").disabled = true;
		document.getElementById("startDate").disabled = true;
	}
	
	//入力不可を解除
	function able(){
		document.getElementById("systemName").disabled = false;
		document.getElementById("languageId").disabled = false;
		document.getElementById("startDate").disabled = false;
	}
	
	$('#create-button').click(function(){
		able();
	})
	
	$('#system-modal').on('show.bs.modal', function (event) {
		
		var button = $(event.relatedTarget);
		var type = button.context.dataset.type;
		var id = button.data('id');
		
		modalReset();
		
		switch(type){
			case 'register':
			document.getElementById("system-form").setAttribute("action", "/system/create");
			$('#modal-title').text('システム登録');
			break;
			case 'edit':
			getone(id);
			document.getElementById("system-form").setAttribute("action", "/system/edit");
			$('#modal-title').text('システム編集');
			break;
			case 'delete':
			getone(id);
			document.getElementById("system-form").setAttribute("action", "/system/delete");
			disable();
			$('#modal-title').text('システム削除');
			break;
		}
	})
	