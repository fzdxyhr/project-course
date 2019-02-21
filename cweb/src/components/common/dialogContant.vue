<template>
	<div>
		<div class="el-dialog__content">
			<slot name="content"></slot>
		</div>
		<div class="el-dialog__footer">
		  	<div class="dialog-footer">
			    <slot name="footer-opt" style="text-align:right;"></slot>
			</div>
		</div>
	</div>
</template>
<script type="text/javascript">
	export default {
		data() {
			return {

			}
		},
		mounted() {
			this.upRjPage();
		},
		methods: {
			upRjPage() {
				if(this.rjPage) {
					this.$parent.rjPage = this.rjPage;
				}
			}
		}
	}
</script>