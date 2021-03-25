<template>
  <div class="container">
    <header class="jumbotron">
        <div v-bind:key="data.id" v-for="data in this.SearchConditionDAO.skillSetSearchResultDAO">
  <b-card
    v-bind:title="data.skillSetName"
    img-src="https://picsum.photos/600/300/?image=25"
    img-alt="Image"
    img-top
    tag="article"
    style="max-width: 20rem;"
    class="mb-2"
  > 
    <b-card-text>
      {{`${data.skillSetDescript.slice(0,100)}`}}
    </b-card-text>
  <section>
  <div>
    <label v-bind:class="{ isActive: card===0}">
      <input type="radio" v-model="card" value="0" checked/>
    <vue-multi-select
      v-model="values"
      :btnLabel="btnLabel"
      search
      historyButton
      :options="options"
      :selectOptions="list" 
      >
  
<template v-slot:option="{option}">
        <input type="checkbox" :checked="option.selected"/>
        <span>{{option.name}}</span>
      </template>

     </vue-multi-select>

    </label>
     </div>
     <div>
    <label>
      <input type="radio" v-model="card" value="3"/>
      <span class="text">Card 3</span>
    </label>
</div>
  </section>
    <b-button @click.prevent="issueTackleClick" variant="primary">Go somewhere</b-button>
  </b-card>
</div>
    </header>
  </div>
</template>

<script>
import SkillSetViewService from '../services/skillSetView.service';
import vueMultiSelect from 'vue-multi-select';
import 'vue-multi-select/dist/lib/vue-multi-select.css';


export default {
  name: 'SkillSetView',
  data() {
    return {
	card:'',
    btnLabel: values =>`${values.length > 0 ? values[0].name : 'Select ...'}`,
    name: 'first group',
    values: [],
    options: {
        multi: false,
		labelName: 'name'
      },
	list:[{ name: '0' },
        { name: '2' },
        { name: '3' },
        { name: '8' },
        { name: '9' },
        { name: '11' },
        { name: '13' },
        { name: '14' },
        { name: '15' },
        { name: '18' }],
      content: '',
      skillSetFlag:false,
      creditFlag:false,
      id: this.$route.params.id,
      SearchConditionDAO:{},
      skillSetSearchResultDAO:{},
      tackleInfo:[{
        username:'',
		skillSetCategoryId:null,
		tackleName:'',
		tackleDescript:'',
		tag:''}], 
      isTackleCreated:false
    };
  },
    computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  components: {
   vueMultiSelect 
  },
  methods: {
    issueTackleClick : function (){	 
	console.log(this.SearchConditionDAO);
	this.tackleInfo[0].skillSetCategoryId = this.SearchConditionDAO.skillSetInfo[0].skillSetCategoryId;
	this.tackleInfo[0].tackleName = this.SearchConditionDAO.skillSetInfo[0].username;
	this.tackleInfo[0].username = this.currentUser.username;
	this.tackleInfo[0].status = 'S';
	this.SearchConditionDAO.tackleInfo =this.tackleInfo;
    console.log(this.SearchConditionDAO);
    SkillSetViewService.issueTackle(this.SearchConditionDAO).then(
    response => {
      
        this.isTackleCreated = response.data

    },
    error => {
       this.content =
         (error.response && error.response.data) ||
         error.message ||
         error.toString();
             }
           );
         },
       },
  mounted() {

    SkillSetViewService.viewSkillSet(this.id).then(
      response => {
        this.content = response.data;
           console.log(this.content);
           this.SearchConditionDAO = response.data;
          this.skillSetSearchResultDAO = this.SearchConditionDAO.skillSetSearchResultDAO[0];
      },
      error => {
        this.content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
  }
};
</script>