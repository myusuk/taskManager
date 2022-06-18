//モーダル内容をリセット
function modalReset(){
	$('#errorMessage').text("");
}
	
//入力値にエラーがあったらモーダルを開く
$(document).ready(function(){
	var error = $('#error').text();
	if(error != ""){
		$('#language-modal').modal('show');
	}
})
	
$('#language-modal').on('show.bs.modal', function (event) {
	var button = $(event.relatedTarget);
		if(button[0] != undefined){
			modalReset();
		}
	$('#modal-title').text('言語登録');
	
})
	