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
    <b-button @click.prevent="issueTackleClick" variant="primary">Go somewhere</b-button>
  </b-card>
</div>
    </header>
  </div>
</template>

<script>
import SkillSetViewService from '../services/skillSetView.service';

export default {
  name: 'SkillSetView',
  data() {
    return {
      content: '',
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
  methods: {
    issueTackleClick : function (){	 
	console.log(this.SearchConditionDAO);
	this.tackleInfo[0].skillSetCategoryId = this.SearchConditionDAO.skillSetInfo[0].skillSetCategoryId;
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