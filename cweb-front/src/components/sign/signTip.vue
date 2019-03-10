<template>
	<div class="sign-tip-index">
		<div style="text-align: left;font-size: 16px;">您今天还没签到，请前往签到</div>
		<div style="text-align: right;margin-top: 20px;">
			<el-button size="small" @click="doCancel">取消</el-button>
			<el-button size="small" type="primary" @click="go2Sign">去签到</el-button>
		</div>
	</div>
</template>

<script>
	export default {
		methods: {
			go2Sign() {
				this.$router.push({
					name: "sign"
				})
			},
			doCancel(){
			  this.closeRjDialog();
			}
		},
	}
</script>

<style lang="scss">
	.sign-tip-index {}
</style>
