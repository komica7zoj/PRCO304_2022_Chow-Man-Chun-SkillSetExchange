<template>



  <div class="container">
    <header class="jumbotron">
	<div> 
	<h3>Hi, let's find you're interesting subject.</h3>
	</div>
	<div>
     Skill set type: 
	<span v-for="(element, index) in skillSetTypeDAO" :key='index' >
       <span v-if="index != 0">-</span>
       <span  style="color:red; background-color:FFFFF;" >
       <a class="btn btn-danger"  @click.prevent="add">{{ element.typeName }}</a>
       </span>
    </span>
	</div>
	<div>
<vue-multi-select
ref="multiSelect"
v-model="skillSetTypeDAO"
search
historyButton
@input="onChangeSkillSetType"
:options="skillSetTypeoptions"
:btnLabel="skillSetTypebtnLabel"
:selectOptions="skillSetTypeDropDownList">
<template v-slot:option="{option}">
        <input type="checkbox" :checked="option.selected"/>
        <span>{{option.typeName}}</span>
      </template>

</vue-multi-select>
</div>

	<div>
     Skill set category: 
	<span v-for="(element, index) in skillSetCategoryDAO" :key='index' >
       <span v-if="index != 0">-</span>
       <span  style="color:green; background-color:EEEEE;" >
       <a class="btn btn-danger"  @click.prevent="add">{{ element.categoryName }}</a>
       </span>
    </span>
	</div>

<div>
<vue-multi-select
ref="multiSelect"
v-model="skillSetCategoryDAO"
search
historyButton
:disabled="skillSetCategoryDropDownListEnable"
:options="skillSetCategoryoptions"
:btnLabel="skillSetCategorybtnLabel"
:selectOptions="skillSetCategoryDropDownList">
<template v-slot:option="{option}">
<input type="checkbox" :checked="option.selected"/>
<span>{{option.categoryName}}</span>
      </template>

</vue-multi-select>
</div>

    </header>
 <a class="btn btn-danger"  @click.prevent="onSearch"> Search </a>
 

<div>
<TableView
      :headers="columns"
      :rows="items"      
      :sort="sort1"
      :pagination="pagination"
      css-style="custom-style"      
    >
      <template v-slot:items="{ row }">
        <td><router-link :to="{ name: 'skillSetView', params: { id: row.id }}">{{ row.skillSetName }}</router-link></td>
        <td>{{ row.skillCategoryName }}</td>   
        <td>{{ row.skillSetDescript }}</td>              
        <td>{{ row.academicLevel }}</td>     
        <td>{{ row.tag }}</td>                 
        <td>{{ row.username }}</td>  
      </template>
      
      <template v-slot:no-data>
        <span>No data</span>
      </template>
    </TableView>

    <div class="separator">---&#10043;---</div>
    <Footer></Footer>
</div>
  </div>
 
 


</template>

<script>
import SearchSkillSetService from '../services/searchSkillSet.service';
import vueMultiSelect from 'vue-multi-select';
import 'vue-multi-select/dist/lib/vue-multi-select.css';
import Footer from '../components/Footer.vue'
import TableView from '../components/TableView.vue'

export default {
  name: 'SearchSkillSet',
  computed: {
        totalSongs () {
            return this.tableData.reduce(function(sum, value ) {
                return sum + value.songs;
            }, 0);
        }
    },
  components: {
   vueMultiSelect ,TableView, Footer
  },
  data() {
    return {
		columns: [
{"label":"Name","field":"skillSetName","sortable":true,"type":"String"},
{"label":"skillCategoryName","field":"skillCategoryName","sortable":true,"type":"String"},
{"label":"skillSetDescript","field":"skillSetDescript","sortable":true,"type":"String"},
{"label":"academicLevel","field":"academicLevel","sortable":true,"type":"Number"},
{"label":"tag","field":"tag","sortable":true,"type":"String"},
{"label":"username","field":"username","sortable":true,"type":"String"},
],
      items: [],  
      sort1:{
        field: 'first_name',
        order: 'asc'
      },
        skillSetTypebtnLabel: skillSetTypeDAO => `Chose (${skillSetTypeDAO.length})`  ,
        skillSetCategorybtnLabel: skillSetCategoryDAO => `Chose (${skillSetCategoryDAO.length})`  ,
        content: '',
        skillSetCategoryDropDownListEnable: true,
        skillSetTypeDAO: [],
        skillSetCategoryDAO: [],
        searchConditionDAO: {
        skillSetType: [],
        skillSetCategory: [],
        tackleInfo: []
       },
      skillSetTypeDropDownList:[{
        name: 'Skill set type:',
        skillSetType: [{id: '', typeName: ''}],
       }],
      skillSetCategoryDropDownList:[{
        name: 'Skill set category:',
        skillSetCategory: [{id: '', categoryName: ''}],
      }],
      skillSetTypeoptions: {
        multi: true,
        groups: true,
		labelName: 'typeName',
        labelList: 'skillSetType',
		groupName: 'name'
      },
       skillSetCategoryoptions: {
        multi: true,
        groups: true,
		labelName: 'categoryName',
        labelList: 'skillSetCategory',
		groupName: 'name'
      },
  pagination:{
        itemsPerPage: 7,
        align: 'center',
        visualStyle: 'select'
      }
    };
  },
  mounted() {
   SearchSkillSetService.getSkillSetPool().then(
      response => {
        this.searchConditionDAO = response.data;
		this.skillSetTypeDropDownList[0].skillSetType = this.searchConditionDAO.skillSetType;
		console.log(this.searchConditionDAO);

      },
      error => {
        this.content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
  },
  methods: {
    onSearch : function (){	 
    let dao ={skillSetType:this.skillSetTypeDAO,skillSetCategory: this.skillSetCategoryDAO,tackleInfo: [], name:this.$store.state.auth.user.username}
    SearchSkillSetService.getSkillSetInfoMatchResult(dao).then(
    response => {
      
        this.items = response.data.skillSetSearchResultDAO

    },
    error => {
       this.content =
         (error.response && error.response.data) ||
         error.message ||
         error.toString();
     }
     );
     },
  add(event) {
      event.target.className += ' disabled'
    },
	onChangeSkillSetType() {
let skillSetTypeDropDownList ={name:'testing',skillSetType:this.skillSetTypeDAO,skillSetCategory: [],tackleInfo: []}
console.log(skillSetTypeDropDownList);
	SearchSkillSetService.getSkillSetFindResult(skillSetTypeDropDownList).then(
     response => {
      this.searchConditionDAO.skillSetCategory = response.data.skillSetCategory;
      this.skillSetCategoryDropDownList[0].skillSetCategory = this.searchConditionDAO.skillSetCategory;
      console.log(this.skillSetCategoryDropDownList[0].skillSetCategory);
       this.skillSetCategoryDropDownListEnable = true;  
       if (this.skillSetCategoryDropDownList[0].skillSetCategory.length > 0 )
       {
          this.skillSetCategoryDropDownListEnable = false;
       }

		console.log(this.skillSetCategoryDropDownListEnable);
      this.skillSetCategoryDAO = [];
    
     },
      error => {
        this.content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
	},
	herfTo: function(idx){  

      if (this.sortEnabled && this.headers[idx].sortable){
       
        if (this.sortColumn == idx){   
          this.sortOrder = (this.sortOrder === 'asc') ? 'desc' : 'asc'
          this.tableRows.reverse()
        }
        else{
          this.sortColumn = idx
          this.sortOrder = 'asc'
          this.sortField = this.headers[idx].field  

          if (this.headers[idx].type == 'Number'){
            this.tableRows.sort((a, b) => a[this.sortField] - b[this.sortField])
          }
          else{
            this.tableRows.sort((a, b) => a[this.sortField].localeCompare(b[this.sortField]))
          }
        }        

        this.page= 1

        this.selectVisibleRows()        
      }
      
    }
	
  },
  
  
};
</script>

<style lang="scss">
@import url(https://fonts.googleapis.com/css?family=Roboto+Mono);
body, html{
  height: 100%;
}
#app {
  font-family: 'Roboto Mono', Helvetica, Arial, sans-serif;
  font-size: 12px;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  width: 90%;
  margin: 0 auto;
}
h1{
  margin-bottom: 2em;
  color: #f90;
}
.separator{
  margin: 2em 0;
  text-align: center;
}
.custom-style {
  // Table styles
  .ozn-table {
    border-collapse: collapse;
    width: 100%;
    thead {
      th { 
        border-bottom: 1px solid #ffffff;
        padding: 0 10px;
        height: 48px;
        text-align: left; 
        font-size: 1em; 
        color: #fff;
        background-color: #7cc3fd; 
        cursor: pointer; 
        &:hover{
          span{
            text-decoration: underline;
            text-decoration-style: dotted;
          }
        }        
        i{
          color: #ffffff; 
          &.active{
            color: #ff00aa;
            + span{
              color: #a9237c;
            }
          }
        }       
      }
    }
    tbody {
      tr{
        &:nth-child(odd){
          background-color: #e9f5ff;
        }
        &:nth-child(even){
          background-color: #fafaeb;
        }
      }
      td {
        border-bottom: 1px solid #ffffff;
        padding: 0 10px;
        height: 48px;
        font-size: 1em; 
      }
    }
  }
  // Paginator styles    
  .ozn-paginator{
    margin-top: .5em;
    select{
      border: 1px solid #7cc3fd;
      border-radius: 8px;
      color: #ffffff;
      background-color:  #7cc3fd;
      outline: none;
    }
    button{
      border: 1px solid #7cc3fd;
      border-radius: 8px;
      color: #ffffff;
      background-color:  #7cc3fd;
      outline: none;
    }    
  }
}
</style>