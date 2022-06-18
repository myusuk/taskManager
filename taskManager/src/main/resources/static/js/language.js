
	
	//入力値にエラーがあったらモーダルを開く
	$(document).ready(function(){
		var error = $('#error').text();
		if(error != ""){
			$('#language-modal').modal('show');
		}
	})
	
	$('#language-modal').on('show.bs.modal', function () {
		
		document.getElementById("language-form").setAttribute("action", "/language/create");
		$('#modal-title').text('言語登録');
	})
	