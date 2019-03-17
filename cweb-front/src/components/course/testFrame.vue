<template>
	<div>
		<iframe id="iframe" src="http://localhost:8081/#/HomeWorkManage" ></iframe>
		<iframe id="iframe" src="http://localhost:8081/#/ClassManage" ></iframe>
		<el-button type="primary" @click="send">发送消息</el-button>
	</div>
</template>

<script>
	export default {
		mounted() {

			console.log("fffffffffffffffffffffffff")
		},
		methods: {
			send() {
				let temp = {};
				temp.name = "aaa";
				temp.url = "sssssss";
				document.getElementById("iframe").contentWindow.postMessage(JSON.parse(JSON.stringify(temp)), "*");
				console.log("rrrrrrrrrrrrrrrr")
			}
		},
	}
</script>

<style lang="scss">
</style>
