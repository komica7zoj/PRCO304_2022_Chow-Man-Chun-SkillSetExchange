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
:btnLabel="skillSetategorybtnLabel"
:selectOptions="skillSetCategoryDropDownList">
<template v-slot:option="{option}">
<input type="checkbox" :checked="option.selected"/>
<span>{{option.categoryName}}</span>
      </template>

</vue-multi-select>
</div>

    </header>
 <a class="btn btn-danger"  @click.prevent="onSearch"> Search </a>
  </div>
  


 


</template>

<script>
import SearchSkillSetService from '../services/searchSkillSet.service';
import vueMultiSelect from 'vue-multi-select';
import 'vue-multi-select/dist/lib/vue-multi-select.css';
export default {
  name: 'SearchSkillSet',
  components: {
   vueMultiSelect,
  },
  data() {
    return {
        skillSetTypebtnLabel: skillSetTypeDAO => `Chose (${skillSetTypeDAO.length})`  ,
        skillSetategorybtnLabel: skillSetTypeDAO => `Chose (${skillSetTypeDAO.length})`  ,
        content: '',
        skillSetCategoryDropDownListEnable: true,
        skillSetTypeDAO: [],
        skillSetCategoryDAO: [],
        searchConditionDAO: {
        skillSetType: [],
        skillSetCategory: [],
        tackleName: []
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
		labelName: 'typeName',
        labelList: 'skillSetCategory',
		groupName: 'name'
      },
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
     },
  add(event) {
      event.target.className += ' disabled'
    },
	onChangeSkillSetType() {
let skillSetTypeDropDownList ={name:'testing',skillSetType:this.skillSetTypeDAO,skillSetCategory: [],tackleName: []}
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
      console.log(this.searchConditionDAO);
     },
      error => {
        this.content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
	}
	
  },
  
  
};
</script>

