<template>
  <div class="container">
    <header class="jumbotron">
<div id="app">
  <div v-if="isTackleCreated === 1">
  tackle transaction completed.
  </div>
  <div v-else-if="isTackleCreated === 0">0</div>
  <div v-else> 
  <h2><b-badge>{{tackleInfo.skillSetName}}</b-badge></h2>
  <h6>Tackle from:{{tackleInfo.username}}</h6>
  <h6>Tackle date: {{tackleInfo.createDate}}</h6>
  <div v-if="tackleInfo.transactionInfoId !== null">
  <h6>Exchange with digital credit: {{tackleInfo.academicLevel}}</h6>
  </div>
<div v-else>
  <h6>Exchange with  skill set: {{tackleInfo.tackleSkillSetName}}</h6>
  </div>
      <div v-bind:style="[baseStyles, overridingStyles]">
              <button v-on:click="makeAgree(currentUser.username, tackleInfo  )"> Agree </button>
    <button v-on:click="makeObject"> Object </button>
  <router-link :to="{ name: 'offerTackle' }"> back </router-link>
  </div>
</div>

</div>

    </header>

  
  </div>

</template>

<script>
import AgreementService from '../services/agreement.service';

export default {
  name: 'Agreement',
  data() {
    return {
	
      items: [],  
      sort1:{
        field: 'first_name',
        order: 'asc'
      },
      content: '',
      skillSetFlag:false,
      creditFlag:false,
      isTackleCreated: '',
      tackleInfo: this.$route.params.tackleInfo,
      SearchConditionDAO:{},
      userSkillSetDAO:[],
      tackleInfoByTacklename:[],
      tackleInfoByusername:[],
    };
  },
    computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  components: {

  },
  methods: {
    makeAgree : function (username,tackleInfo){	 
    tackleInfo.tackleName = username;
    AgreementService.Agree(tackleInfo).then(
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

console.log(this.tackleInfo);
  }
};
</script>