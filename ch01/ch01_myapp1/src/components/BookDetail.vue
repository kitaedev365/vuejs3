<template> 
    <div>
        <div class="modal-header border-bottom-0">
            <h1 class="modal-title fs-5">{{ book.title }}</h1>
            <!-- 닫기 버튼에 이벤트 emit 적용 -->
            <button type="button" class="btn-close" @click="$emit('closeDetail')" ></button>
        </div>
        <hr />
        <img :src="book.picUrl" :alt="book.title" style="max-width:200px;"/>
        <hr />
        <div class="modal-body py-0">
            <p>카테고리: {{ book.cate }} / 출간일: {{ book.pubdate }}</p>
            <p>페이지 수: {{ book.pages }} / 가격: {{ book.price }}원</p>
        </div>
        <div class="modal-footer flex-column align-items-stretch w-100 gap-2 pb-3 border-top-0">
            <button type="button" class="btn btn-lg btn-primary">Buy</button>
            <!-- 닫기 버튼에 이벤트 emit 적용 -->
            <button type="button" @click="$emit('closeDetail')" class="btn btn-lg btn-secondary">Close</button>
        </div>
    </div>
</template>

<script>
export default {
    name: "BookDetail",
    props: {
        book: {
            type: Object,
            required: true
        }
    }
}
</script>