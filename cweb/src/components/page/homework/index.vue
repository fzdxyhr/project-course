<template>
	<div class="homework-index">
		<div class="button-group">
			<el-input placeholder="请输入内容" v-model="key">
				<i slot="suffix" class="el-input__icon el-icon-search"></i>
			</el-input>
			<el-button type="primary" @click="go2Publish">发布作业</el-button>
		</div>
		<div class="table-content diyscrollbar">
			<el-table :data="tableData" style="width: 100%">
				<el-table-column prop="homework_title" label="作业标题">
				</el-table-column>
				<el-table-column prop="publish_teacher_name" label="发布老师">
				</el-table-column>
				<el-table-column prop="homework_desc" label="作业描述">
				</el-table-column>
				<el-table-column prop="create_time" label="创建时间" min-width="90px">
				</el-table-column>
				<el-table-column label="操作" min-width="110px">
					<template scope="scope">
						<el-button icon="el-icon-view" type="text" @click="go2HomeworkDetail(scope.row)">查看作业提交</el-button>
						<el-button icon="el-icon-view" type="text" @click="exportSubmitStudent(scope.row)">导出提交学生</el-button>
						<el-button icon="el-icon-edit" type="text" @click="go2Update(scope.row)">修改</el-button>
						<el-button icon="el-icon-delete" style="color: #f56c6c;" type="text" @click="go2Delete(scope.row)">删除</el-button>
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
	import homeworkAdd from '@components/page/homework/homeworkAdd.vue'
	import studentList from '@components/page/homework/studentList.vue'
	import exportSelect from '@components/page/homework/exportSelect.vue'

	export default {
		components: {
			rjDialog,
			homeworkAdd,
			studentList,
			exportSelect
		},
		data() {
			return {
				key: "",
				tableData: [],
				page_no: 1,
				page_size: 10,
				total: 0
			};
		},
		mounted() {
			this.go2Query();
		},
		methods: {
			handleSizeChange(val) {
				this.page_size = val;
				this.go2Query();
			},
			handleCurrentChange(val) {
				this.page_no = val;
				this.go2Query();
			},
			go2Query() {
				const loading = this.$loading({
					lock: true,
					text: '加载中...',
					spinner: 'el-icon-loading',
					background: 'rgba(0, 0, 0, 0.7)'
				});
				this.$axios.get('/v1/homeworks?key=' + this.key + "&page_no=" + this.page_no + "&page_size=" + this.page_size).then(
					(
						response) => {
						loading.close()
						this.tableData = response.data.items;
						this.total = response.data.total;
					}, (response) => {
						loading.close()
						this.$message.error('获取作业失败');
					});
			},
			exportSubmitStudent(row){
				this.rjDialog.
				title("导出作业提交学生").
				width("700px").
				top("").
				currentView(exportSelect, {
					data: row
				}).
				closeOnClickModal(false).
				showClose(true).
				then((opt) => {
				}).show();
			},
			go2Update(row) {
				this.rjDialog.
				title("修改作业").
				width("600px").
				top("").
				currentView(homeworkAdd, {
					data: row
				}).
				closeOnClickModal(false).
				showClose(true).
				then((opt) => {
					this.go2Query();
				}).show();
			},
			go2HomeworkDetail(row){
				this.rjDialog.
				title("查看作业提交情况").
				width("700px").
				top("").
				currentView(studentList, {
					data: row
				}).
				closeOnClickModal(false).
				showClose(true).
				then((opt) => {
					this.go2Query();
				}).show();
			},
			go2Delete(row) {
				this.$confirm('此操作将永久删除该作业, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$axios.delete('/v1/classes/' + row.id).then((
						response) => {
						this.$message({
							type: 'success',
							message: '删除作业成功!'
						});
						this.go2Query();
					}, (response) => {
						this.$message.error('删除作业失败');
					});
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					});
				});
			},
			go2Publish() {
				this.rjDialog.
				title("发布作业").
				width("600px").
				top("").
				currentView(homeworkAdd, {}).
				closeOnClickModal(false).
				showClose(true).
				then((opt) => {
					this.go2Query();
				}).show();
			}
		},
	}
</script>

<style lang="scss">
	.homework-index {
		.button-group {
			margin-left: 10px;
			margin-top: 10px;

			.el-input {
				width: 250px;
			}
		}
		.table-content {
			height: calc(100% - 110px);
			overflow-y: auto;
      overflow-x: hidden;
		}

		.paging {
			float: right;
			margin-top: 10px;
		}
	}
</style>
