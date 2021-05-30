<template>
  <div class="container">
    <header class="jumbotron">
<div id="app">
 <div>
<TableView
      :headers="columns"
      :rows="tackleInfoByTacklename"      
      :sort="sort1"
      :pagination="pagination"
      css-style="custom-style"      
    >
      <template v-slot:items="{ row }">
<td> {{row.id }}</td>
<td> {{row.createDate }}</td>
<td> {{row.academicLevel }}</td>
<td> {{row.skillCategoryName }}</td>
<td> {{row.skillSetName}}</td>
<td> {{row.tackleDescript }}</td>
<td> {{row.tag}}</td>
<td> {{row.tackleSkillSetName}}</td>
<td> {{row.tackleName }}</td>
<td> {{row.transactionInfoId }}</td>
<td> {{row.username }}</td>
<td><router-link :to="{ name: 'agreement', params: { tackleInfo: row }}">accept the tacke</router-link></td>
      </template>
      
      <template v-slot:no-data>
        <span>No data</span>
      </template>
    </TableView>

    <div class="separator">---&#10043;---</div>
    <Footer></Footer>
</div>
<div>
<TableView
      :headers="columns"
      :rows="tackleInfoByusername"      
      :sort="sort1"
      :pagination="pagination"
      css-style="custom-style"      
    >
      <template v-slot:items="{ row }">
<td> {{row.id }}</td>
<td> {{row.createDate }}</td>
<td> {{row.academicLevel }}</td>
<td> {{row.skillCategoryName }}</td>
<td> {{row.skillSetName}}</td>
<td> {{row.tackleDescript }}</td>
<td> {{row.tag}}</td>
<td> {{row.tackleSkillSetName}}</td>
<td> {{row.tackleName }}</td>
<td> {{row.transactionInfoId }}</td>
<td> {{row.username }}</td>

      </template>
      
      <template v-slot:no-data>
        <span>No data</span>
      </template>
    </TableView>

    <div class="separator">---&#10043;---</div>
    <Footer></Footer>
</div>
 
</div>
       
    </header>
  </div>
</template>

<script>
import offerTackleService from '../services/offerTackle.service';

import Footer from '../components/Footer.vue'
import TableView from '../components/TableView.vue'


export default {
  name: 'OfferTackle',
  data() {
    return {
		columns: [
{"label":"id","field":"id" ,"sortable":true,"type":"String"},
{"label":"createDate","field":"createDate" ,"sortable":true,"type":"Date"},
{"label":"Level","field":"academicLevel" ,"sortable":true,"type":"Number"},
{"label":"Category","field":"skillCategoryName" ,"sortable":true,"type":"Number"},
{"label":"SkillSetName","field":"skillSetName" ,"sortable":true,"type":"String"},
{"label":"Description","field":"tackleDescript" ,"sortable":true,"type":"String"},
{"label":"Tag","field":"tag"  ,"sortable":true,"type":"String"},
{"label":"TackleSkillSetName","field":"tackleSkillSetName" ,"sortable":true,"type":"String"},
{"label":"TackleName","field":"tackleName" ,"sortable":true,"type":"String"},
{"label":"transactionInfoId","field":"transactionInfoId" ,"sortable":true,"type":"Number"},
{"label":"username","field":"username" ,"sortable":true,"type":"String"},
{"label":"Accept tackle","field":"accept" ,"sortable":false,"type":"String"},
],
      items: [],  
      sort1:{
        field: 'first_name',
        order: 'asc'
      },
      content: '',
      skillSetFlag:false,
      creditFlag:false,
      id: this.$route.params.id,
      SearchConditionDAO:{},
      userSkillSetDAO:[],
      tackleInfoByTacklename:[],
      tackleInfoByusername:[],
      tackleInfo:[{
        username:'',
		skillSetCategoryId:null,
		tackleName:'',
		tackleDescript:'',
		tag:''}], 
      isTackleCreated:null,
  pagination:{
        itemsPerPage: 7,
        align: 'center',
        visualStyle: 'select'
      }
    };
  },
    computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  components: {
   TableView, Footer
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
    offerTackleService.acceptTackle(this.SearchConditionDAO).then(
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

    offerTackleService.viewTackle(this.currentUser.username).then(
      response => {
        this.content = response.data;
           console.log(this.content);
this.tackleInfoByTacklename = response.data.tackleInfoByTacklename;
this.tackleInfoByusername = response.data.tackleInfoByusername;

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