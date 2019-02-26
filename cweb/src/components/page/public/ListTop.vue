<template>
	<div class="top">
		<el-input v-bind:placeholder="placeholder" v-model="searchValue">
		</el-input>
		<el-button @click="search" type="primary" icon="el-icon-search">查询</el-button>
		<el-button type="primary" @click="addBtn">{{btnText}}</el-button>
	</div>
</template>

<script>
	export default {
		name: 'listTop',
		props: ['placeholder', 'btnText'],
		data() {
			return {
				searchValue: '',
			}
		},
		methods: {
			search() {
				this.$emit('search', this.searchValue);
			},
			addBtn() {
				this.$emit('addBtn');
			},
		}
	}
</script>

<style lang="scss">
	.top {
		box-sizing: border-box;
		padding: 10px;
		height: 56px;
		overflow: hidden;
		border-bottom: 1px solid #ddd;

		.el-input {
			width: 250px;
		}
	}
</style>
