timerID = setInterval('clock()',500); //0.5秒毎にclock()を実行

clock = ()=> {
	document.getElementById("view_clock").innerHTML = getNow();
}

getNow = ()=> {
	let now = new Date();
	let year = now.getFullYear();
	let mon = now.getMonth()+1; //１を足すこと
	let day = now.getDate();
	let dow = now.getDay();
	let hour = now.getHours();
	let min = now.getMinutes();
	let sec = now.getSeconds();

    let w = new Array("日","月","火","水","木","金","土");
	//出力用
	let s = year + "年" + mon + "月" + day + "日 (" + w[dow] + ")" + hour + "時" + min + "分" + sec + "秒";
	return s;
}