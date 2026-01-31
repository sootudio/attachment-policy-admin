(() => {
    const API = {
        fixed: "/api/policy/fixed-extensions",
        custom: "/api/policy/custom-extensions",
    };

    const $msg = document.getElementById("message");
    const $fixedLine = document.getElementById("fixedLine");

    const $customInput = document.getElementById("customInput");
    const $btnAdd = document.getElementById("btnAdd");

    const $customChips = document.getElementById("customChips");
    const $customEmpty = document.getElementById("customEmpty");
    const $customCount = document.getElementById("customCount");

    // 메시지를 화면에 표시(성공/실패)
    function showMessage(type, text) {
        $msg.className = "msg " + (type === "ok" ? "ok" : "err");
        $msg.textContent = text;
        $msg.style.display = "block";

        clearTimeout(showMessage._t);
        showMessage._t = setTimeout(() => { $msg.style.display = "none"; }, 2500);
    }

    // 확장자 입력값을 정규화(공백 제거, 소문자 변환, 앞의 점 제거)
    function normalizeExtension(raw) {
        let s = (raw ?? "").trim().toLowerCase();
        if (s.startsWith(".")) s = s.slice(1);
        return s;
    }

    // ApiResponse 기반으로 요청/응답을 처리
    async function requestJson(url, options = {}) {
        const res = await fetch(url, options);
        const text = await res.text();

        let body = null;
        if (text) {
            try { body = JSON.parse(text); } catch (_) { body = null; }
        }

        // 에러코드는 사용자에게 노출 X message만 노출
        if (!res.ok) {
            const msg = body?.message || "요청 처리 중 오류가 발생했습니다.";
            throw new Error(msg);
        }

        return body; // ApiResponse
    }

    // ===== 고정 확장자 =====
    async function loadFixed() {
        $fixedLine.innerHTML = "";

        const resp = await requestJson(API.fixed);
        const items = resp?.data ?? [];

        for (const it of items) {
            const label = document.createElement("label");
            label.className = "fixed-item";

            const cb = document.createElement("input");
            cb.type = "checkbox";
            cb.checked = !!it.blocked;

            const name = document.createElement("span");
            name.textContent = it.extension;

            label.appendChild(cb);
            label.appendChild(name);

            // 체크 변경 시 서버에 즉시 반영(PATCH, 멱등)
            cb.addEventListener("change", async () => {
                cb.disabled = true;
                try {
                    await requestJson(`${API.fixed}/${encodeURIComponent(it.extension)}`, {
                        method: "PATCH",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify({ blocked: cb.checked }),
                    });
                    showMessage("ok", "저장되었습니다.");
                } catch (e) {
                    // 실패하면 UI를 원상복구한다
                    cb.checked = !cb.checked;
                    showMessage("err", e.message);
                } finally {
                    cb.disabled = false;
                }
            });

            $fixedLine.appendChild(label);
        }
    }

    // ===== 커스텀 확장자 =====
    async function loadCustom() {
        $customChips.innerHTML = "";

        const resp = await requestJson(API.custom);
        const items = resp?.data ?? [];

        // count를 보드 내부에 표시한다
        $customCount.textContent = `${items.length}/200`;
        $customEmpty.style.display = (items.length === 0) ? "block" : "none";

        for (const it of items) {
            const chip = document.createElement("div");
            chip.className = "chip";

            const ext = document.createElement("span");
            ext.textContent = it.extension;

            const x = document.createElement("button");
            x.className = "x";
            x.type = "button";
            x.textContent = "X";
            x.title = "삭제";

            // X 클릭 시 삭제(DELETE)
            x.addEventListener("click", async () => {
                x.disabled = true;
                try {
                    await requestJson(`${API.custom}/${it.id}`, { method: "DELETE" });
                    showMessage("ok", "삭제되었습니다.");
                    await loadCustom();
                } catch (e) {
                    showMessage("err", e.message);
                    x.disabled = false;
                }
            });

            chip.appendChild(ext);
            chip.appendChild(x);
            $customChips.appendChild(chip);
        }
    }

    // 커스텀 확장자를 추가(POST)
    async function createCustom() {
        const ext = normalizeExtension($customInput.value);

        // UI 검증(서버 검증이 있지만 보조로 사용)
        if (!ext) return showMessage("err", "확장자를 입력해주세요.");
        if (ext.length > 20) return showMessage("err", "확장자는 최대 20자까지 입력할 수 있습니다.");

        $btnAdd.disabled = true;
        try {
            await requestJson(API.custom, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ extension: ext }),
            });
            $customInput.value = "";
            showMessage("ok", "추가되었습니다.");
            await loadCustom();
        } catch (e) {
            showMessage("err", e.message);
        } finally {
            $btnAdd.disabled = false;
        }
    }

    // 초기 로드
    document.addEventListener("DOMContentLoaded", async () => {
        try {
            await loadFixed();
            await loadCustom();
        } catch (e) {
            showMessage("err", e.message);
        }

        $btnAdd.addEventListener("click", createCustom);
        $customInput.addEventListener("keydown", (e) => {
            if (e.key === "Enter") createCustom();
        });
    });
})();