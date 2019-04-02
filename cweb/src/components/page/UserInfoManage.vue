<template>
	<div class="user-info-index">
		<div class="top-content">
			<el-input v-model="key" placeholder="请输入用户名或者账号"></el-input>
			<el-button type="primary" @click="findUsers" icon="el-icon-search">查询</el-button>
			<el-button v-if="role ==='admin'" type="primary" @click="go2Add" icon="el-icon-plus">新增用户</el-button>
			<el-button v-if="role ==='teacher'" type="primary" @click="go2Add" icon="el-icon-plus">新增学生</el-button>
			<!-- <el-button type="primary" @click="go2Import">导入用户</el-button> -->
			<el-button type="primary" @click="go2BatchDelete">批量删除</el-button>
			<span style="margin-left: 10px;color: rgb(147, 153, 159);">批量导入学生可前往班级管理中导入</span>
		</div>
		<div class="user-info diyscrollbar">
			<el-table :data="tableData" border @selection-change="handleSelectionChange">
				<el-table-column type="selection" width="55">
				</el-table-column>
				<el-table-column prop="user_name" label="用户名">
				</el-table-column>
				<el-table-column prop="account" label="账号">
				</el-table-column>
				<el-table-column prop="class_name" label="班级名称">
				</el-table-column>
				<el-table-column prop="role" label="角色">
					<template scope="scope">
						{{scope.row.role == 'admin'? "管理员" : scope.row.role == 'teacher' ? "老师" : "学生"}}
					</template>
				</el-table-column>
				<el-table-column prop="sex" label="性别">
					<template scope="scope">
						{{scope.row.sex == 1?"男":"女"}}
					</template>
				</el-table-column>
				<el-table-column prop="status" label="状态">
					<template scope="scope">
						{{scope.row.status == 0?"失效":"有效"}}
					</template>
				</el-table-column>
				<!-- <el-table-column prop="is_admin" label="是否管理员">
					<template scope="scope">
						{{scope.row.is_admin == 0?"否":"是"}}
					</template>
				</el-table-column> -->
				<el-table-column prop="create_time" label="创建时间" min-width="120px">
				</el-table-column>
				<el-table-column label="操作" min-width="140px">
					<template scope="scope">
						<el-button size="small" icon="el-icon-edit" type="primary" @click="handleUpdate(scope.row)">修改</el-button>
						<el-button size="small" icon="el-icon-delete" type="danger" @click="handleDelete(scope.row)">删除</el-button>
					</template>
				</el-table-column>
			</el-table>
		</div>
		<div class="paging">
			<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page.page_no"
			 :page-sizes="[10, 20, 50, 100]" :page-size="page.page_size" layout="total, sizes, prev, pager, next, jumper" :total="page.total">
			</el-pagination>
		</div>
		<rjDialog></rjDialog>
	</div>
</template>

<script>
	import rjDialog from '@components/common/dialog.vue'
	import UploadImportData from '@components/common/UploadImportData.vue'
	import addUser from '@components/page/user/addUser.vue'

	export default {
		components: {
			rjDialog,
			addUser,
			UploadImportData
		},
		data() {
			return {
				tableData: [],
				selectedData: [],
				role: "",
				page: {
					page_no: 1,
					page_size: 10,
					total: 0
				},
				key: ""
			}
		},
		mounted() {
			this.findUsers();
			const user = JSON.parse(localStorage.getItem("USER"));
			if (user) {
				this.role = user.role;
			}
		},
		computed: {
			host() {
				return this.$store.state.host
			}
		},
		methods: {
			handleSelectionChange(val) {
				this.selectedData = val;
			},
			findUsers() {
				const loading = this.$loading({
					lock: true,
					text: '加载中...',
					spinner: 'el-icon-loading',
					background: 'rgba(0, 0, 0, 0.7)'
				});
				this.$axios.get("/v1/users?page_no=" + this.page.page_no + "&page_size=" + this.page.page_size + "&key=" + this.key)
					.then((response) => {
						loading.close()
						let message = response.data;
						this.tableData = message.items;
						this.page.total = message.total;
					}, (response) => {
						loading.close()
						this.$message.error('获取用户失败');
					});
			},
			handleUpdate(row) {
				this.rjDialog.
				title(this.role === 'admin' ? "修改用户" : "修改学生").
				width("700px").
				top("").
				currentView(addUser, {
					data: row
				}).
				closeOnClickModal(false).
				showClose(true).
				then((opt) => {
					this.findUsers();
				}).show();
			},
			go2Add() {
				this.rjDialog.
				title(this.role === 'admin' ? "新增用户" : "新增学生").
				width("700px").
				top("").
				currentView(addUser, {}).
				closeOnClickModal(false).
				showClose(true).
				then((opt) => {
					this.findUsers();
				}).show();
			},
			go2Import() {
				this.rjDialog.
				title("导入学生").
				width("600px").
				top("8%").
				currentView(UploadImportData, {
					uploadUrl: this.host + "/v1/users/import",
					downloadUrl: this.host + "/v1/users/download"
				}).
				closeOnClickModal(false).
				showClose(true).
				then((opt) => {
					this.findUsers();
				}).show();
			},
			go2BatchDelete() {
				if (this.selectedData.length == 0) {
					this.$message.error('请先选择用户');
					return;
				}
				this.$confirm('确认删除选中用户吗', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					let ids = [];
					this.selectedData.forEach((item) => {
						ids.push(item.id);
					})
					const loading = this.$loading({
						lock: true,
						text: '加载中...',
						spinner: 'el-icon-loading',
						background: 'rgba(0, 0, 0, 0.7)'
					});
					this.$axios.delete("/v1/users/batch?ids=" + ids).then((response) => {
						loading.close();
						this.$message.success('批量删除用户成功');
						this.findUsers();
					}, (response) => {
						loading.close();
						this.$message.error('批量删除用户失败');
					});
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					});
				});
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
			handleDelete(row) {
				this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$axios.delete("/v1/users/" + row.id).then((response) => {
						let message = response.data;
						this.findUsers();
						this.$message.success('删除用户成功');
					}, (response) => {
						this.$message.error('删除用户失败');
					});
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					});
				});
			}
		}
	}
</script>

<style lang="scss">
	.user-info-index {
		.el-table {
			margin: 5px;
			width: calc(100% - 10px);
		}
		.top-content .el-input {
			width: 240px;
		}

		.user-info {
			height: calc(100% - 120px);
			overflow-y: auto;
			overflow-x: hidden;
		}

		.paging {
			text-align: right;
			margin-top: 15px;
		}

		.top-content {
			margin: 10px 10px;
		}
	}
</style>
