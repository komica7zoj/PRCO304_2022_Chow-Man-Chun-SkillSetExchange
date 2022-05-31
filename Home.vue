<template>
  <div class="container">
    <header class="jumbotron">
      <div class="event-card">
    <div class="card">
      <div class="card-content">
        <h2 class="is-size-4 has-text-weight-bold">Show case</h2>
        <small class="event-date"></small>
        <span>This web site can help you to find the free skill set what you have interesting</span>
      </div>
    </div>
     <router-link to="searchSkillSet">search page</router-link>
    <div class="item1" />
      <router-link to="user">asset</router-link>
      <div class="item2" />

  </div>

    </header>
  </div>
</template>

<script>
import UserService from '../services/user.service';

export default {
  name: 'Home',
  data() {
    return {
      content: ''
    };
  },
  mounted() {
    UserService.getPublicContent().then(
      response => {
        this.content = response.data;
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
<style lang="scss" scoped>
  .item1 {
    background-image: url(../../themes/searchpage.png);
    height: 200px;
    background-position: left;
    background-size: cover;
    text-align: center;
    width: 50%;
    padding: 10px;
    padding-top: 50px;
      h2 {
      margin-top: 10px;
    }
  } 
  .item2 {
    background-image: url(../../themes/asset.png);
    height: 200px;
    background-position: left;
    background-size: cover;
    text-align: center;
    width: 100%;
    padding: 10px;
    padding-top: 50px;
      h2 {
      margin-top: 10px;
    }
  }
  .card {
    background-image: url('https://placekitten.com/400/400');
    height: 200px;
    background-position: center;
    background-size: cover;
    text-align: center;
  }
  .card-content {
    padding-top: 50px;
    position: absolute;
    color: #FFF;
    background-color: rgba(0, 0, 0, 0.35);
    top: 0;
    padding: 10px;
    height: 200px;
    width: 100%;
    span {
      font-size: 18px;
      text-align: center;
      width: 100%;
      position: absolute;
      bottom: 10px;
      right: 0;
    }
    h2 {
      margin-top: 10px;
    }
  }
  .event-date {
    background-color: #151515;
    color: #FFF;
    font-size: .75em;
    padding: 2px 10px;
    position: absolute;
    top: 0;
    right: 0;
  }
</style>