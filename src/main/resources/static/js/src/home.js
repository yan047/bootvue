// register modal component
Vue.component('modal1', {
  template: '#modal-template'
})

var vm = new Vue({
	el: '#app',
	data: {
		models:[],
		showModal: false
	},
	mounted:function(){
        this.onLoad();
	},
	methods:{
	    onLoad:function(){
	    }
     }
})
