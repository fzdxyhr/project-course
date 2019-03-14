<template>
	<div class="class-index">
		<div class="search-content">
			<span>班级名称: </span>
			<el-input v-model="key" placeholder="请输入班级名称"></el-input>
			<el-button type="primary" icon="el-icon-search" @click="findClasses">查询</el-button>
			<el-button type="primary" icon="el-icon-plus" @click="go2Add">添加班级</el-button>
		</div>
		<div class="table-content">
			<el-table :data="tableData" style="width: 100%">
				<el-table-column prop="class_name" label="班级名称">
				</el-table-column>
				<el-table-column prop="teacher_name" label="授课老师">
				</el-table-column>
        <el-table-column prop="progress" label="教学进度" min-width="50px">
          <template scope="scope">{{scope.row.progress == null? 0 : scope.row.progress}}%</template>
        </el-table-column>
				<el-table-column prop="monitor_name" label="班长">
				</el-table-column>
				<el-table-column prop="max_student" label="班级人数" min-width="50px">
				</el-table-column>
				<el-table-column prop="class_desc" label="班级描述">
				</el-table-column>
				<el-table-column prop="create_time" label="创建时间"  min-width="90px">
				</el-table-column>
				<el-table-column label="操作" min-width="160px">
					<template scope="scope">
						<el-button icon="el-icon-setting" type="text" @click="go2SetMonitor(scope.row)">设置班长</el-button>
						<el-button icon="el-icon-upload" style="color: #67c23a;" type="text" @click="handleImport(scope.row)">导入学生</el-button>
						<el-button icon="el-icon-view" style="color: #67c23a;" type="text" @click="go2ViewStudent(scope.row)">查看学生</el-button>
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
	import studentList from '@components/page/class/studentList.vue'
	import UploadImportData from '@components/common/UploadImportData.vue'

	export default {
		components: {
			rjDialog,
			classesAdd,
			setMonitor,
			studentList,
			UploadImportData
		},
		data() {
			return {
				key: "",
				tableData: [],
				page_no: 1,
				page_size: 10,
				total: 0
			}
		},
		mounted() {
			this.findClasses();
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
			handleDelete(row) {
				this.$confirm('此操作将永久删除该教师, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$axios.delete('/v1/classes/' + row.id).then((
						response) => {
						this.$message({
							type: 'success',
							message: '删除教师成功!'
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
			handleImport(row) {
				this.rjDialog.
				title("导入学生").
				width("600px").
				top("8%").
				currentView(UploadImportData, {
					uploadUrl: "http://localhost:8085/v1/users/import?class_id=" + row.id,
					downloadUrl: "http://localhost:8085/v1/users/download"
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
				title("查看学生").
				width("850px").
				top("").
				currentView(studentList, {
					data: row
				}).
				closeOnClickModal(false).
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
		}

		.paging {
			margin-top: 15px;
			text-align: right;
		}
	}
</style>
