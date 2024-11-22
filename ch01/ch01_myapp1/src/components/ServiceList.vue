<template>
    <div>
        <div class="album py-5 bg-body-tertiary">
            <div class="container">
                <h2>상품 정보</h2>
                <hr />
                <ul class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-4">
                    <li v-for="(book, i) in data" :key="i" class="col">
                        <div class="card shadow-sm">
                            <img :src="`${book.picUrl}`" :alt="`${book.title}`">
                            <div class="card-body">
                                <h4 class="title" :style="rtext">{{ book.title }}</h4>
                                <p class="card-text">카테고리: {{ book.cate }}</p>
                                <p class="card-text"></p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <button @click="increseUp(i)" class="btn btn-sm btn-outline-secondary">추천</button> 
                                        <button @click="isDetail=true; selectedBook=i" class="btn btn-sm btn-outline-secondary">상세보기</button>
                                    </div>
                                    <small>추천수: <span>{{ book.sug }}</span></small>
                                </div>    
                            </div>
                        </div>
                    </li>
                </ul>  
            </div>
        </div>
        <!-- 상세보기 섹션 -->
        <div class="modal modal-sheet d-block bg-body-secondary p-4 py-md-5" v-if="isDetail">
            <div class="modal-dialog">
                <div class="modal-content rounded-4 shadow">
                    <!-- 자식 컴포넌트에서 closeDetail 이벤트를 수신 -->
                    <BookDetail :book="data[selectedBook]" @closeDetail="isDetail = false" />
                </div>
            </div>
        </div>  
    </div>
</template>

<script>
import BookDetail from "./BookDetail.vue";
import { data } from "../assets/books";

export default {
    name: "serviceList",
    components: {
        BookDetail
    },
    data() {
        return {
            rtext: "color: red",
            isDetail: false,
            data: data,
            selectedBook: 0,  // 선택한 교재의 인덱스
        }
    },
    methods: {
        increseUp(i) {
            this.data[i].sug += 1;
        }
    }
}
</script>

<style scoped>
html, body {
    margin: 0;
    padding: 0;
}
ul {
    list-style: none;
}
.modal.modal-sheet {
    position: fixed;
    z-index: 999;
    box-sizing: border-box;
    max-width: 100vw;
    max-height: 100vh;
    overflow: hidden;
}
.card img {
    box-shadow:0px 1px 3px #ccc;
}
.card .title {
    padding-top: 0.5em;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
</style>