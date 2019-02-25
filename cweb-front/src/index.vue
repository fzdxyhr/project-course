<template>
	<div class="home">
		<v-head></v-head>
		<div class="content">
			<router-view></router-view>
		</div>
		<v-footer></v-footer>
	</div>
</template>

<script>
	import vHead from '@components/public/Nav.vue';
	import vFooter from '@components/public/Footer.vue';
	export default {
		name: "Home",
		components: {
			vHead,
			vFooter
		},
		data() {
			return {}
		}
	}
</script>

<style lang="scss">
.content {
  position: relative;
}
</style>
