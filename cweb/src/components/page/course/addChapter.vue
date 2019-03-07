<template>
	<div class="add-chapter-index">
		<el-form label-width="80px" :model="form" ref="form">
			<el-form-item label="章节名称">
				<el-input v-model="form.chapter_name"></el-input>
			</el-form-item>
			<el-form-item label="章节描述">
				<el-input type="textarea" :rows="4" v-model="form.chapter_desc"></el-input>
			</el-form-item>
		</el-form>
		<div class="obj-content">对象信息</div>
		<el-form label-width="100px" :inline="true" :model="form" ref="childForm">
			<div class="child-chapter-content" v-for="(item,index) in form.course_chapters">
				<el-form-item label="子章节标题">
					<el-input size="small" v-model="item.chapter_name"></el-input>
				</el-form-item>
				<el-form-item label="子章节内容">
					<fileUpload isImg="file" style="width: 110px;margin-top: -2px;" @change="fileChange(item)" buttonText="上传文件"
					 :showFileList="false" :limit="2" :fileList="item.file_list"></fileUpload>
				</el-form-item>
				<el-form-item v-if="item.file_list.length > 0" style="width: 160px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">
					<span :title="obj.name" v-for="obj in item.file_list">{{obj.name.substring(0,10)}}...</span>
				</el-form-item>
				<el-form-item>
					<i class="el-icon-minus" style="font-size: 18px;cursor: pointer;" @click="deleteChapter(index)" v-if="index > 0"></i>
					<i class="el-icon-plus" style="font-size: 18px;cursor: pointer;margin-left: 8px;" @click="addChapter" v-if="index == (form.course_chapters.length -1)"></i>
				</el-form-item>
			</div>
		</el-form>
		<div class="dialog-footer">
			<el-button @click="doCancel">取 消</el-button>
			<el-button @click="onSubmit" type="primary">确 定</el-button>
		</div>
	</div>
</template>

<script>
	import fileUpload from '@components/common/file-upload.vue';

	export default {
		components: {
			fileUpload
		},
		data() {
			return {
				chapterId: "",
				form: {
					course_id: "",
					chapter_name: "",
					chapter_desc: "",
					course_chapters: [{
						chapter_name: "",
						chapter_parent_id: "",
						chapter_type: "",
						chapter_file_path: "",
						file_list: []
					}]
				}
			}
		},
		mounted() {
			if (this.rjDialogParams().data) {
				this.form.course_id = this.rjDialogParams().data.id;
			}
			if (this.rjDialogParams().chapter) {
				this.chapterId = this.rjDialogParams().chapter.id;
				this.form.chapter_name = this.rjDialogParams().chapter.chapter_name;
				this.form.chapter_desc = this.rjDialogParams().chapter.chapter_desc;
				this.form.course_chapters = this.rjDialogParams().chapter.course_chapter_vos;
				this.form.course_chapters.forEach((item) => {
					item.file_list = [];
					let temp = {};
					if (item.chapter_file_path) {
						temp.name = item.chapter_file_path.split("/")[item.chapter_file_path.split("/").length - 1];
						temp.url = item.chapter_file_path;
					}
					item.file_list.push(temp);
				})
			}
		},
		methods: {
			onSubmit() {
				let req = {};
				if (this.form.course_chapters && this.form.course_chapters.length > 0) {
					let tempList = [];
					this.form.course_chapters.forEach((item) => {
						let temp = {};
						temp.chapter_name = item.chapter_name;
						temp.chapter_parent_id = item.chapter_parent_id;
						temp.chapter_type = item.chapter_type;
						temp.chapter_file_path = item.chapter_file_path;
						tempList.push(temp);
					})
					req.course_chapter_vos = tempList;
				}
				req.course_id = this.form.course_id;
				req.chapter_name = this.form.chapter_name;
				req.chapter_desc = this.form.chapter_desc;
				if (this.chapterId) {
					this.$axios.put("/v1/course_chapters/" + this.chapterId, req).then((response) => {
						this.$message.success('修改章节成功');
						this.closeRjDialog();
					}, (response) => {
						this.$message.error('修改章节失败');
					});
				} else {
					this.$axios.post("/v1/course_chapters", req).then((response) => {
						this.$message.success('添加章节成功');
						this.closeRjDialog();
					}, (response) => {
						this.$message.error('添加章节失败');
					});
				}
			},
			doCancel() {
				this.closeRjDialog();
			},
			fileChange(item) {
				if (item.file_list && item.file_list.length > 0) {
					let file = item.file_list[0];
					if (file.name.toLowerCase().indexOf("doc") >= 0 || file.name.toLowerCase().indexOf("docx") >= 0) {
						item.chapter_type = 1;
					}
					if (file.name.toLowerCase().indexOf("ppt") >= 0 || file.name.toLowerCase().indexOf("pptx") >= 0) {
						item.chapter_type = 2;
					}
					if (file.name.toLowerCase().indexOf("mp4") >= 0) {
						item.chapter_type = 3;
					}
					item.chapter_file_path = file.url;
				}
			},
			addChapter() {
				this.form.course_chapters.push({
					chapter_name: "",
					chapter_parent_id: "",
					chapter_type: "",
					chapter_file_path: "",
					file_list: []
				});
			},
			deleteChapter(index) {
				this.form.course_chapters.splice(index, 1);
			}
		},
	}
</script>

<style lang="scss">
	.add-chapter-index {
		.dialog-footer {
			text-align: right;
		}

		.obj-content {
			padding: 10px;
			border-bottom: solid 1px rgba(57, 67, 87, 0.2);
			margin-bottom: 10px;
		}

		.child-chapter {
			.el-input__inner {
				width: 250px;
			}
		}
	}
</style>
