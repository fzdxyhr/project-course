<template>
	<div class="logout-index">
		<div class="body-content">您将要退出系统，请确认？</div>
		<div class="button-group">
			<el-button type="primary" @click="logout">退出</el-button>
			<el-button>取消</el-button>
		</div>
	</div>
</template>

<script>
	export default {
		methods: {
			logout() {
				localStorage.removeItem("WEBFRONT_USER");
				this.$router.push({name:"login"});
			}
		},
	}
</script>

<style lang="scss">
	.logout-index {
		
	}
</style>
