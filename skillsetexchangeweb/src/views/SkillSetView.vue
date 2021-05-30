<template>
  <div class="container">
    <header class="jumbotron">
<div id="app">
  <div v-if="isTackleCreated === 1">
  <router-link :to="{ name: 'offerTackle' }">Click here to preview your offer.</router-link>
  </div>
  <div v-else-if="isTackleCreated === 0">0</div>
  <div v-else> <div v-bind:key="data.id" v-for="data in this.SearchConditionDAO.skillSetSearchResultDAO">
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
	<label> Skill set description: </label>
    <label>   {{`${data.skillSetDescript.slice(0,100)}`}} </label>
    </b-card-text>
  <section>
  <div>
    <label v-bind:class="{ isActive: card===0}">
      <input type="radio" v-model="card" v-bind:value="false" checked/>
    <vue-multi-select
      v-model="values"
      :btnLabel="btnLabel"
      search
      historyButton
      :options="options"
      :selectOptions="list" 
      :disabled="card"
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
      <input type="radio" v-model="card" v-bind:value="true"/>
      <span class="text">Credit {{skillSetSearchResultDAO.academicLevel}}</span>
    </label>
</div>
  </section>
  <label> Tag: </label>
  <input type="text" v-model="skillSetSearchResultDAO.tag"/>
    <b-button @click.prevent="issueTackleClick" variant="primary" :disabled="card == false && values.length == 0">Make Tackle</b-button>

  </b-card>
</div></div>
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
	card:false,
    btnLabel: values =>`${values.length > 0 ? values[0].name : 'Select ...'}`,
    name: 'first group',
    values: [],
    options: {
        multi: false,
		labelName: 'name',
		labelValue: 'id'
      },
	list:[],
      content: '',
      skillSetFlag:false,
      creditFlag:false,
      id: this.$route.params.id,
      SearchConditionDAO:{},
      userSkillSetDAO:[],
      skillSetSearchResultDAO:{},
      tackleInfo:[{
        username:'',
		skillSetCategoryId:null,
		tackleName:'',
		tackleDescript:'',
		tag:''}], 
      isTackleCreated:null
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
	this.tackleInfo[0].skillSetInfoId = this.SearchConditionDAO.skillSetInfo[0].id;
	this.tackleInfo[0].username = this.currentUser.username;
	this.tackleInfo[0].status = 'S';
    if (this.card)
	{
	this.tackleInfo[0].statusType = 'C';
	this.tackleInfo[0].tackleSkillSetInfoId=0;
	}
	else
	{
	this.tackleInfo[0].tackleSkillSetInfoId = this.values[0].id;
	this.tackleInfo[0].statusType = 'S';
	}
	this.SearchConditionDAO.tackleInfo =this.tackleInfo;
    console.log(this.SearchConditionDAO);
    SkillSetViewService.issueTackle(this.SearchConditionDAO).then(
    response => {
      
        this.isTackleCreated = response.data
console.log(this.isTackleCreated );
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

    SkillSetViewService.viewSkillSet(this.id, this.currentUser.username).then(
      response => {
        this.content = response.data;
           console.log(this.content);
           this.SearchConditionDAO = response.data;
          this.skillSetSearchResultDAO = this.SearchConditionDAO.skillSetSearchResultDAO[0];
          this.userSkillSetDAO = this.SearchConditionDAO.userSkillSetDAO;
          this.userSkillSetDAO.forEach((value, index) => {
    this.list.push({name:value.skillSetName, id:value.id});
    console.log(value);
    console.log(index);
});
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