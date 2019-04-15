<template>
	<div class="front-index">
		<div class="header-div">
			<el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal">
				<el-menu-item index="1">首页</el-menu-item>
				<el-menu-item index="2">培训与认证</el-menu-item>
				<el-menu-item index="3">价格</el-menu-item>
				<el-menu-item index="4">开发者</el-menu-item>
				<el-menu-item index="5">合作伙伴</el-menu-item>
			</el-menu>
		</div>
	</div>
</template>

<script>
	import header from './header.vue'

	export default {
		components: {
			header
		},
		data() {
			return {
				activeIndex:"1"
			};
		}
	}
</script>

<style lang="scss">
	.front-index {
		.header-div {
			background: #333;
			height: 100px;
			min-width: 1200px;
			display: flex;
			justify-content: center;

			.el-menu--horizontal>.el-menu-item {
				height: 100px;
				line-height: 100px;
			}

			.el-menu {
				background-color: #333;
			}

			.el-menu--horizontal>.el-menu-item.is-active {
				color: #00b0ff;
			}
			.el-menu--horizontal>.el-menu-item.is-active {
				border-bottom: 3px solid #00b0ff;
				background: rgba(0, 0, 0, 0.1);
				font-weight: bold;
			}
			.el-menu--horizontal>.el-menu-item:not(.is-disabled):hover {
				background: rgba(0, 0, 0, 0.1);
				color: #EEE;
			}
			.el-menu--horizontal>.el-menu-item {
				color: #EEE;
			}
			.el-menu--horizontal>.el-menu-item {
				border-bottom: 2px solid #333;
			}
		}
	}
</style>
