<template>
	<div>
		<div class="secction">
			<list-top placeholder="输入标签名查找" btnText="添加标签" @addBtn="addTag" @search="searchTagAction"></list-top>
			<tags-list @sonGetTags="getTags" @sonDelTag="delTag" @sonAmendTag="amendTag" :sonTagsList="tagsList"></tags-list>

		</div>
		<el-dialog :title="dialog.title" :visible.sync="dialog.control" size="small" width="40%">
			<el-form ref="dialog.form" :model="dialog.form" label-width="80px">
				<el-form-item label="标签名">
					<el-input v-model="dialog.form.tag_name"></el-input>
				</el-form-item>
				<el-form-item label="描述">
					<el-input type="textarea" :rows="3" v-model="dialog.form.tag_desc"></el-input>
				</el-form-item>
			</el-form>
			<span slot="footer" class="dialog-footer">
    			<el-button @click="dialog.control = false">取 消</el-button>
    			<el-button type="primary" @click="onSubmit">确 定</el-button>
 			 </span>
		</el-dialog>
	</div>
</template>

<script>
	import listTop from './public/ListTop.vue'
	import tagsList from './tags/TagsList.vue'
	export default {
		name: 'tagsManage',
		components: {
			'list-top': listTop,
			'tags-list': tagsList,
		},
		data() {
			return {
				dialog: {
					title: '',
					control: false, //控制dialog的显示与关闭
					form: {
						tag_name: '', //名
						tag_desc: '', //描述
					},
				},
				tagsList: '',
				key:""
			}
		},
		created() {
			this.getTags();
		},
		methods: {
			getTags() { //获取数据
				this.$axios.get('/v1/tags?key='+this.key+"&page_no=1&page_size=10000000").then((response) => {
					this.tagsList = response.data.items;
				}, (response) => {
					this.$message.error('获取标签失败');
				});
			},
			addTag() { //显示添加的dialog
				this.dialog.form = {
					id: '',
					tag_name: '', //名
					tag_desc: '', //描述
				};
				this.dialog.control = true;
				this.dialog.title = "添加标签";
			},
			amendTag(tag) { //显示修改的dialog
				this.dialog.form = JSON.parse(JSON.stringify(tag));
				this.dialog.control = true;
				this.dialog.title = "修改标签";
			},
			delTag(tag) {
				this.$confirm('此操作将永久删除标签"' + tag.tag_name + '", 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$axios.post("url", {
						"id": tag.id
					}).then((response) => {
						this.$message.error('删除标签成功');
					}, (response) => {
						this.$message.error('删除标签失败');
					});
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					});
				});
			},
			searchTagAction(key) { //搜索按钮
				this.key = key;
				this.getTags();
			},
			onSubmit() { //处理添加或修改标签
				if(!this.dialog.form.id) { //添加标签
					this.$axios.post("/v1/tags",this.dialog.form).then((response) => {
						this.dialog.control = false;
						this.getTags();
						this.$message.success('添加成功');
					}, (response) => {
						this.$message.error('添加失败');
					});
				} else { //修改标签
					this.$axios.put("/v1/tags/"+this.dialog.form.id, this.dialog.form).then((response) => {
						this.dialog.control = false;
						this.$message.success('修改成功');
						this.getTags();
					}, (response) => {
						this.$message.error('修改失败');
					});
				}

			},
		},
	}
</script>

<style>

</style>