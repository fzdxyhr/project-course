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
			<div class="child-chapter-content" v-for="(item,index) in form.childChapters">
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
					<i class="el-icon-plus" style="font-size: 18px;cursor: pointer;margin-left: 8px;" @click="addChapter" v-if="index == (form.childChapters.length -1)"></i>
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
				form: {
					course_id:"",
					chapter_name: "",
					chapter_desc: "",
					childChapters: [{
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
		},
		methods: {
			onSubmit() {
				this.$axios.post("/v1/course_chapters", this.form).then((response) => {
					this.$message.success('添加章节成功');
				}, (response) => {
					this.$message.error('添加章节失败');
				});
			},
			doCancel() {
				this.closeRjDialog();
			},
			fileChange(item) {
				if (item.file_list && item.file_list.length > 0) {
					let file = item.file_list[0];
					if (file.name.toLowcase().indexof("doc") >= 0 || file.name.toLowcase().indexof("docx") >= 0) {
						item.chapter_type = 1;
					}
					if (file.name.toLowcase().indexof("ppt") >= 0 || file.name.toLowcase().indexof("pptx") >= 0) {
						item.chapter_type = 2;
					}
					if (file.name.toLowcase().indexof("mp4") >= 0) {
						item.chapter_type = 3;
					}
					item.chapter_file_path = file.url;
				}
			},
			addChapter() {
				this.form.childChapters.push({
					chapter_name: "",
					chapter_parent_id: "",
					chapter_type: "",
					chapter_file_path: "",
					file_list: []
				});
			},
			deleteChapter(index) {
				this.form.childChapters.splice(index, 1);
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
