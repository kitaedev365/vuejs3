<template>
  <div class="container">
    <h1 class="center-align">
      Dashboard
    </h1>
    <p class="center-align">
      Welcome to the dashboard!
    </p>
    <hr>
    <figure class="center-align">
      <img
        src="https://picsum.photos/seed/picsum/800/200"
        alt="Banner Image"
        class="responsive-img"
      >
    </figure>
    <div class="row">
      <div class="col s12 m4">
        <div class="card blue lighten-1">
          <div class="card-content white-text">
            <span class="card-title">Users</span><p>Active Users: 350</p>
          </div>
        </div>
      </div>
      <div class="col s12 m4">
        <div class="card green lighten-1">
          <div class="card-content white-text">
            <span class="card-title">Sales</span><p>Monthly Sales: $12,300</p>
          </div>
        </div>
      </div>
      <div class="col s12 m4">
        <div class="card red lighten-1">
          <div class="card-content white-text">
            <span class="card-title">Feedback</span><p>Feedback Received: 89</p>
          </div>
        </div>
      </div>
      <div class="col s12">
        <div class="card yellow lighten-4">
          <div class="card-content black-text">
            <span class="card-title">Notifications</span>
            <ul>
              <li>New user signed up</li><li>Product update available</li>
              <li>Monthly report is ready</li>
            </ul>
          </div>
        </div>
      </div>
      <div class="col s12">
        <div class="card grey lighten-3">
          <div class="card-content black-text">
            <span class="card-title">Recent Activity</span>
            <table class="highlight responsive-table">
              <thead>
                <tr>
                  <th>Activity</th><th>Date</th><th>Status</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>User Login</td><td>2024-10-25</td> <td>Success</td>
                </tr>
                <tr>
                  <td>Product Purchase</td> <td>2024-10-24</td> <td>Completed</td>
                </tr>
                <tr>
                  <td>Feedback Submitted</td> <td>2024-10-23</td><td>Pending</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
</script>    