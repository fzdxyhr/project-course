<template>
	<div class="homework-index">
		<div class="button-group">
			<el-input placeholder="请输入内容" v-model="key">
				<i slot="suffix" class="el-input__icon el-icon-search"></i>
			</el-input>
			<el-button type="primary" @click="go2Publish">发布作业</el-button>
		</div>
		<div class="table-content">
			<el-table :data="tableData" style="width: 100%">
				<el-table-column prop="homework_title" label="作业标题">
				</el-table-column>
				<el-table-column prop="publish_teacher" label="发布老师">
				</el-table-column>
				<el-table-column prop="homework_desc" label="作业描述">
				</el-table-column>
				<el-table-column prop="create_time" label="创建时间" min-width="90px">
				</el-table-column>
				<el-table-column label="操作" min-width="160px">
					<template scope="scope">
						<el-button icon="el-icon-setting" type="text" @click="go2Update(scope.row)">修改</el-button>
						<el-button icon="el-icon-upload" style="color: #67c23a;" type="text" @click="go2Delete(scope.row)">删除</el-button>
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

	export default {
		components: {
			rjDialog,
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
		methods: {
			handleSizeChange(val) {
				this.page_size = val;
				this.go2Query();
			},
			handleCurrentChange(val) {
				this.page_no = val;
				this.go2Query();
			},
			go2Query(){
				this.$axios.get('/v1/classes?key=' + this.key + "&page_no=" + this.page_no + "&page_size=" + this.page_size).then((
					response) => {
					this.tableData = response.data.items;
					this.total = response.data.total;
				}, (response) => {
					this.$message.error('获取作业失败');
				});
			},
			go2Update(row){
				
			},
			go2Delete(row){
				
			},
			go2Publish(){
				
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
		.paging {
			float: right;
			margin-top: 10px;
		}
	}
</style>
