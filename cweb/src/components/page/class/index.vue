<template>
	<div class="class-index">
		<div class="search-content">
			<span>班级名称: </span>
			<el-input v-model="key" placeholder="请输入班级名称"></el-input>
			<el-button type="primary" icon="el-icon-search" @click="findClasses">查询</el-button>
			<el-button v-if="role === 'admin'" type="primary" icon="el-icon-plus" @click="go2Add">添加班级</el-button>
		</div>
		<div class="table-content diyscrollbar">
			<el-table :data="tableData" style="width: 100%">
				<el-table-column show-overflow-tooltip prop="class_name" label="班级名称" min-width="50px">
				</el-table-column>
				<el-table-column show-overflow-tooltip prop="teacher_name" label="授课老师" min-width="50px">
				</el-table-column>
				<el-table-column show-overflow-tooltip prop="progress" label="教学进度" min-width="50px">
					<template scope="scope">{{scope.row.progress == null? 0 : scope.row.progress}}%</template>
				</el-table-column>
				<!-- <el-table-column show-overflow-tooltip prop="monitor_name" label="班长" min-width="60px">
				</el-table-column> -->
				<!-- <el-table-column show-overflow-tooltip prop="max_student" label="班级人数" min-width="50px">
				</el-table-column> -->
				<el-table-column show-overflow-tooltip prop="class_desc" label="班级描述">
				</el-table-column>
				<el-table-column show-overflow-tooltip prop="create_time" label="创建时间" min-width="80px">
				</el-table-column>
				<el-table-column label="操作" min-width="170px">
					<template scope="scope">
						<!-- <el-button icon="el-icon-setting" type="text" @click="go2SetMonitor(scope.row)">设置班长</el-button> -->
						<el-button icon="el-icon-setting" type="text" @click="go2SetSignTime(scope.row)">设置签到时间</el-button>
						<el-button icon="el-icon-upload" style="color: #67c23a;" type="text" @click="handleImport(scope.row)">导入学生</el-button>
						<el-button icon="el-icon-view" style="color: #67c23a;" type="text" @click="go2ViewStudent(scope.row)">查看学生签到</el-button>
						<el-button icon="el-icon-download" style="color: #67c23a;" type="text" @click="handleExport(scope.row)">导出学生</el-button>
						<el-button icon="el-icon-edit" type="text" @click="handleUpdate(scope.row)">修改</el-button>
						<el-button icon="el-icon-delete" style="color: #f56c6c;" type="text" @click="handleDelete(scope.row)">删除</el-button>
					</template>
				</el-table-column>
			</el-table>
		</div>
		<div class="paging">
			<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page_no"
			 :page-sizes="[10, 20, 50, 100,500]" :page-size="page_size" layout="total, sizes, prev, pager, next, jumper" :total="total">
			</el-pagination>
		</div>
		<rjDialog></rjDialog>
	</div>
</template>

<script>
	import rjDialog from '@components/common/dialog.vue'
	import classesAdd from '@components/page/class/classesAdd.vue'
	import setMonitor from '@components/page/class/setMonitor.vue'
	import setSignTime from '@components/page/class/setSignTime.vue'
	import studentList from '@components/page/class/studentList.vue'
	import UploadImportData from '@components/common/UploadImportData.vue'

	export default {
		components: {
			rjDialog,
			classesAdd,
			setMonitor,
			studentList,
			UploadImportData,
			setSignTime
		},
		data() {
			return {
				key: "",
				tableData: [],
				page_no: 1,
				page_size: 10,
				total: 0,
				role:""
			}
		},
    computed: {
    	host() {
    		return this.$store.state.host
    	}
    },
		mounted() {
			this.findClasses();
			const user = JSON.parse(localStorage.getItem("USER"));
			if (user && user.role) {
				this.role = user.role;
			}
			// 			if (window.addEventListener) {
			// 				window.addEventListener('message', function(e) {
			// 					console.log("tttttttttttttttttt",e)
			// 				}, false);
			// 			} else if (window.attachEvent) {
			// 				window.attachEvent('onmessage', function(e) {
			// 					console.log("ddddddddddddddddddddd",e)
			// 				});
			// 			}
		},
		methods: {
			handleSizeChange(val) {
				this.page_size = val;
				this.findClasses();
			},
			handleCurrentChange(val) {
				this.page_no = val;
				this.findClasses();
			},
			findClasses() {
				const loading = this.$loading({
					lock: true,
					text: '加载中...',
					spinner: 'el-icon-loading',
					background: 'rgba(0, 0, 0, 0.7)'
				});
				this.$axios.get('/v1/classes?key=' + this.key + "&page_no=" + this.page_no + "&page_size=" + this.page_size).then((
					response) => {
					loading.close();
					this.tableData = response.data.items;
					this.total = response.data.total;
				}, (response) => {
					loading.close();
					this.$message.error('获取班级失败');
				});
			},
			handleExport(row){
				window.location = this.host+"/v1/classes/"+row.id+"/students/export";
			},
			handleDelete(row) {
				this.$confirm('此操作将永久删除该班级, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$axios.delete('/v1/classes/' + row.id).then((
						response) => {
						this.$message({
							type: 'success',
							message: '删除班级成功!'
						});
						this.findClasses();
					}, (response) => {
						this.$message.error('删除班级失败');
					});
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					});
				});
			},
			go2SetMonitor(row) {
				this.rjDialog.
				title("设置班长").
				width("600px").
				top("").
				currentView(setMonitor, {
					data: row
				}).
				closeOnClickModal(false).
				showClose(true).
				then((opt) => {
					this.findClasses();
				}).show();
			},
			go2SetSignTime(row) {
				this.rjDialog.
				title("设置签到时间").
				width("600px").
				top("").
				currentView(setSignTime, {
					data: row
				}).
				closeOnClickModal(false).
				showClose(true).
				then((opt) => {
					this.findClasses();
				}).show();
			},
			handleImport(row) {
				this.rjDialog.
				title("导入学生").
				width("600px").
				top("8%").
				currentView(UploadImportData, {
					uploadUrl: this.$store.state.host + "/v1/users/import?class_id=" + row.id,
					downloadUrl: this.$store.state.host + "/v1/users/download"
				}).
				showClose(true).
				then((opt) => {
					this.findUsers();
				}).show();
			},
			handleUpdate(row) {
				this.rjDialog.
				title("修改班级").
				width("600px").
				top("").
				currentView(classesAdd, {
					data: row
				}).
				closeOnClickModal(false).
				showClose(true).
				then((opt) => {
					this.findClasses();
				}).show();
			},
			go2Add() {
				this.rjDialog.
				title("新增班级").
				width("600px").
				top("").
				currentView(classesAdd, {}).
				closeOnClickModal(false).
				showClose(true).
				then((opt) => {
					this.findClasses();
				}).show();
			},
			go2ViewStudent(row) {
				this.rjDialog.
				title("查看学生签到").
				width("850px").
				top("").
				currentView(studentList, {
					data: row
				}).
				closeOnClickModal(false).
				sizeSelf("class-student-index").
				showClose(true).
				then((opt) => {
					this.findClasses();
				}).show();
			}
		},

	}
</script>

<style lang="scss">
	.class-index {
		padding: 10px;
		font-size: 14px;

		.search-content {
			.el-input {
				width: 250px;
			}
		}

		.table-content {
			margin-top: 10px;
			height: calc(100% - 120px);
			overflow-y: auto;
			overflow-x: hidden;
		}

		.paging {
			margin-top: 15px;
			text-align: right;
		}
	}
</style>
