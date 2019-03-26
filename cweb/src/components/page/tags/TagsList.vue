<template>
	<div @scroll="getModeTags" class="tags-list diyscrollbar">
		<div v-if="sonTagsList.length == 0" style="text-align: center;margin-top: 15%;color: #48576a;">
			暂无标签
		</div>
		<div v-if="sonTagsList.length > 0">
			<el-card v-for="tag in sonTagsList" class="box-card">
				<div slot="header" class="card-top clearfix">
					<span :title="tag.tag_name" class="tag">{{tag.tag_name}}</span>
					<el-button @click="delTag(tag)" type="danger" icon="el-icon-delete"></el-button>
					<el-button @click="amendTag(tag)" type="primary" icon="el-icon-edit"></el-button>
				</div>
				<div class="t-describe" :title="tag.tag_desc">
					{{tag.tag_desc}}
				</div>
			</el-card>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'tagsList',
		props: ['sonTagsList'],
		components: {},
		data() {
			return {}
		},
		methods: {
			getModeTags() {
				var top = this.$el.scrollHeight - this.$el.clientHeight - this.$el.scrollTop
				if (!top) {
					this.$emit('sonGetTags', 10, '');
				}

			},
			amendTag(tag) {
				this.$emit('sonAmendTag', tag);
			},
			delTag(tag) {
				this.$emit('sonDelTag', tag);
			},
		}
	}
</script>

<style lang="scss">
	.tags-list {
		position: relative;
		padding: 5px 10px;
		overflow: auto;
		height: calc(100% - 66px);


		.clearfix:before,
		.clearfix:after {
			display: table;
			content: "";
		}

		.clearfix:after {
			clear: both;
		}

		.box-card {
			width: 250px;
			margin: 5px;
			float: left;
		}

		.box-card .el-card__header {
			padding: 10px;
		}

		.card-top {}

		.card-top .el-button {
			float: right;
		}

		.card-top .el-button {
			margin-left: 5px;
			padding: 7px;
		}

		.tag {
			display: inline-block;
			font-size: 13px;
			border: 1px solid #20A0FF;
			padding: 0px 10px;
			line-height: 24px;
			border-radius: 40px;
			cursor: pointer;
			color: #1D8CE0;
			max-width: 130px;
			overflow: hidden;
			white-space: nowrap;
			text-overflow: ellipsis;
		}

		.tag:hover {
			background-color: #eee;
		}

		.t-describe {
			height: 64px;
			font-size: 13px;
			line-height: 22px;
			/*仅仅webkit内核(移动端多数为webkit内核)*/
			display: -webkit-box !important;
			overflow: hidden;
			text-overflow: ellipsis;
			word-break: break-all;
			-webkit-box-orient: vertical;
			-webkit-line-clamp: 3;
			/*行数*/
		}

	}
</style>
