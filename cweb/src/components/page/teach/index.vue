<template>
	<div class="teach-index">
		<div class="button-group">
			<el-input placeholder="请输入内容" v-model="key">
				<i slot="suffix" class="el-input__icon el-icon-search"></i>
			</el-input>
			<el-button type="primary" @click="go2Publish">新增教学情况</el-button>
		</div>
		<div class="teach-table-content diyscrollbar">
			<el-table :data="tableData" style="width: 100%" tooltip-effect="dark">
				<el-table-column show-overflow-tooltip prop="teach_classes_name" label="教学班级">
				</el-table-column>
				<el-table-column show-overflow-tooltip prop="teach_content_name" label="教学内容" min-width="120px">
				</el-table-column>
				<el-table-column show-overflow-tooltip prop="teach_evaluation" label="教学评价" min-width="50px">
					<template slot-scope="scope">
						{{comment[scope.row.teach_evaluation]}}
					</template>
				</el-table-column>
				<el-table-column show-overflow-tooltip prop="teach_remark" label="教学说明" min-width="120px">
				</el-table-column>
				<el-table-column prop="teach_time" label="教学时间" min-width="90px" show-overflow-tooltip>
				</el-table-column>
				<el-table-column label="操作" min-width="110px">
					<template scope="scope">
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
	import teachAdd from '@components/page/teach/teachAdd.vue'

	export default {
		components: {
			rjDialog,
			teachAdd
		},
		data() {
			return {
				key: "",
				tableData: [],
				page_no: 1,
				page_size: 10,
				total: 0,
				comment:{
					1:"很差",
					2:"差",
					3:"中等",
					4:"良好",
					5:"优秀"
				}
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
				this.$axios.get('/v1/teachs?key=' + this.key + "&page_no=" + this.page_no + "&page_size=" + this.page_size).then(
					(
						response) => {
						loading.close()
						this.tableData = response.data.items;
						this.total = response.data.total;
					}, (response) => {
						loading.close()
						this.$message.error('获取教学失败');
					});
			},
			go2Update(row) {
				this.rjDialog.
				title("修改教学信息").
				width("800px").
				top("").
				currentView(teachAdd, {
					data: row
				}).
				closeOnClickModal(false).
				showClose(true).
				then((opt) => {
					this.go2Query();
				}).show();
			},
			go2Delete(row) {
				this.$confirm('此操作将永久删除该教学信息, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$axios.delete('/v1/teachs/' + row.id).then((
						response) => {
						this.$message({
							type: 'success',
							message: '删除教学信息成功!'
						});
						this.go2Query();
					}, (response) => {
						this.$message.error('删除教学信息失败');
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
				title("新增教学信息").
				width("800px").
				top("").
				currentView(teachAdd, {}).
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
	.teach-index {
		.button-group {
			margin-left: 10px;
			margin-top: 10px;

			.el-input {
				width: 250px;
			}
		}

		.teach-table-content {
			height: calc(100% - 106px);
			overflow-y: auto;
      overflow-x: hidden;
		}

		.paging {
			float: right;
			margin-top: 10px;
		}
	}
</style>
