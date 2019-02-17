<template>
	<div>
		<div class="top-content">
			<el-button type="primary" @click="go2Add">新增用户</el-button>
			<el-button type="primary">导入用户</el-button>
		</div>
		<div class="secction diyscrollbar">
			<el-table :data="tableData" border>
				<el-table-column prop="user_name" label="用户名">
				</el-table-column>
				<el-table-column prop="sex" label="性别">
					<template scope="scope">
						{{scope.row.sex == 1?"男":"女"}}
					</template>
				</el-table-column>
				<el-table-column prop="class_id" label="班级标识">
				</el-table-column>
				<el-table-column prop="status" label="状态">
					<template scope="scope">
						{{scope.row.status == 0?"失效":"有效"}}
					</template>
				</el-table-column>
				<el-table-column prop="is_admin" label="是否管理员">
					<template scope="scope">
						{{scope.row.is_admin == 0?"否":"是"}}
					</template>
				</el-table-column>
				<el-table-column prop="role" label="角色">
					<template scope="scope">
						{{scope.row.role == 'teacher'?"老师":"学生"}}
					</template>
				</el-table-column>
				<el-table-column prop="create_time" label="创建时间" min-width="120px">
				</el-table-column>
				<el-table-column label="操作" min-width="100px">
					<template scope="scope">
						<el-button size="small" type="primary" @click="handleUpdate(scope.$index, scope.row)">修改</el-button>
						<el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
					</template>
				</el-table-column>
			</el-table>
			<div class="paging">
				<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page.page_no"
				 :page-sizes="[10, 20, 50, 100]" :page-size="page.page_size" layout="total, sizes, prev, pager, next, jumper" :total="page.total">
				</el-pagination>
			</div>

		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				tableData: [],
				page:{
					page_no:1,
					page_size:10,
					total:0
				}
			}
		},
		mounted() {
			this.findUsers();
		},
		methods: {
			findUsers(){
				this.$http.get("/v1/users?page_no="+this.page.page_no+"&page_size="+this.page.page_size).then((response) => {
					let message = response.data;
					this.tableData = message.items;
					this.page.total = message.total;
				}, (response) => {
					this.$message.error('获取用户失败');
				});
			},
			handleUpdate(){
				
			},
			go2Add(){
				
			},
			handleSizeChange(val) {
				this.page.page_size = val;
				this.findUsers();
				console.log(`每页 ${val} 条`);
			},
			handleCurrentChange(val) {
				this.page.page_no = val;
				this.findUsers();
				console.log(`当前页: ${val}`);
			},
			handleDelete(index, row) {
				this.$http.delete("/v1/users/"+row.id).then((response) => {
					let message = response.data;
					this.findUsers();
					this.$message.success('删除用户成功');
				}, (response) => {
					this.$message.error('删除用户失败');
				});
			}
		}
	}
</script>

<style>
	.el-table {
		margin: 5px;
		width: calc(100% - 10px);
	}

	.paging {
		text-align: right;
		margin-top: 15px;
	}
	.top-content {
		margin: 10px 0;
	}
</style>
