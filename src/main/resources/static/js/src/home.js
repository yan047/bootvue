// register modal component
Vue.component('modal1', {
  template: '#modal-template'
})
// register modal component
Vue.component('modal2', {
  template: '#modal-template2'
})

var vm = new Vue({
	el: '#app',
	data: {
		models:[],
		showModal1: false,
		showModal2: false
	},
	mounted:function(){
        this.onLoad();
	},
	methods:{
	    onLoad:function(){
	    }
     }
})
