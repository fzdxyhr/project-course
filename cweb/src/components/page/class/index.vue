<template>
	<div class="class-index">
		<div class="search-content">
			<span>班级名称: </span>
			<el-input size="small" v-model="key" placeholder="请输入班级名称"></el-input>
			<el-button size="small" type="primary" icon="el-icon-search">查询</el-button>
		</div>
		<div class="table-content">
			<el-table :data="tableData" style="width: 100%">
				<el-table-column prop="date" label="日期" width="180">
				</el-table-column>
				<el-table-column prop="name" label="姓名" width="180">
				</el-table-column>
				<el-table-column prop="address" label="地址">
				</el-table-column>
			</el-table>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				key: "",
				tableData:[]
			}
		},
		mounted() {

		},
		methods: {

		},

	}
</script>

<style lang="scss">
	.class-index {
		padding: 10px;
		font-size: 14px;
		
		.el-input {
			width: 250px;
		}

		.search-content {
			
		}

		.table-content {
			margin-top: 10px;
		}
	}
</style>
